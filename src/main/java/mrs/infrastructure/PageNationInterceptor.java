package mrs.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.HandlerInterceptor;

public class PageNationInterceptor implements HandlerInterceptor {
    private int startPage = 1;

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        startPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        PageHelper.startPage(startPage, PageNation.PAGE_SIZE);
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) return;

        PageInfo<Object> pageInfo = (PageInfo<Object>) modelAndView.getModel().get("pageInfo");
        if (pageInfo != null) {
            int page = pageInfo.getPageNum();
            int endPage = pageInfo.getPages();
            long totalPage = pageInfo.getTotal();
            PageNation pageNation = new PageNation(page, startPage, endPage, totalPage);
            modelAndView.addObject("pageNation", pageNation);
        }
    }
}
