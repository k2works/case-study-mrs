package mrs.presentation.user;

import com.github.pagehelper.PageInfo;
import mrs.application.service.user.UserManagementService;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

/**
 * 利用者一覧画面
 */
@Controller("利用者の管理")
@PreAuthorize("hasRole('管理者')")
@RequestMapping("/users")
public class UserController {
    private final UserManagementService userManagementService;
    private final Message message;

    public UserController(UserManagementService userManagementService, Message messageSource) {
        this.userManagementService = userManagementService;
        this.message = messageSource;
    }

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    String userList(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        List<User> users = userManagementService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("users", users);
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }

    @PostMapping(params = "regist")
    String userCreate(Model model, @Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return userList(model);
        }
        try {
            User user = new User(form.getUserId(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getRoleName());
            this.userManagementService.regist(user);
            model.addAttribute("success", message.getMessageByKey("user_regist"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return userList(model);
        }
        return userList(model);
    }

    @PostMapping(params = "update")
    String userUpdate(Model model, @Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return userList(model);
        }
        try {
            User user = new User(form.getUserId(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getRoleName());
            this.userManagementService.update(user);
            model.addAttribute("success", message.getMessageByKey("user_update"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return userList(model);
        }
        return userList(model);
    }

    @GetMapping("delete/{id}")
    String userDelete(Model model, @PathVariable String id, Principal principal) {
        try {
            if (id.equals(principal.getName()))
                throw new AccessDeniedException(message.getMessageByKey("user_delete_exception"));
            UserId userId = new UserId(id);
            User user = this.userManagementService.findOne(userId);
            if (user.getReservations().size() > 0)
                throw new AccessDeniedException(message.getMessageByKey("user_reserved_delete_exception"));

            this.userManagementService.delete(userId);
            model.addAttribute("success", message.getMessageByKey("user_delete"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return userList(model);
        }
        return userList(model);
    }
}
