package mrs.infrastructure;

public class PageNation {
    public static final int PAGE_SIZE = 10;
    Integer page;
    Integer startPage;
    Integer endPage;
    Long totalPage;

    public PageNation(int page, int startPage, int endPage, long totalPage) {
        this.page = page;
        this.startPage = startPage;
        this.endPage = endPage;
        this.totalPage = totalPage;
    }

    public Integer Page() {
        return page;
    }

    public Integer StartPage() {
        return startPage;
    }

    public Integer EndPage() {
        return endPage;
    }

    public Long TotalPage() {
        return totalPage;
    }
}
