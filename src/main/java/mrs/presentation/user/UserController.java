package mrs.presentation.user;

import mrs.application.service.auth.UserManagementService;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
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
import java.util.Locale;

/**
 * 利用者一覧画面
 */
@Controller("利用者の管理")
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/users")
public class UserController {
    private final UserManagementService userManagementService;
    private final MessageSource messagesource;

    public UserController(UserManagementService userManagementService, MessageSource messageSource) {
        this.userManagementService = userManagementService;
        this.messagesource = messageSource;
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
    String userList(Model model) {
        List<User> users = userManagementService.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @PostMapping(params = "regist")
    String userCreate(Model model, @Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return userList(model);
        }
        User user = new User(form.getUserId(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getRoleName());
        try {
            this.userManagementService.regist(user);
            model.addAttribute("success", getMessageSourceMessage("user_regist"));
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
        User user = new User(form.getUserId(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getRoleName());
        try {
            this.userManagementService.update(user);
            model.addAttribute("success", getMessageSourceMessage("user_update"));
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
                throw new AccessDeniedException(getMessageSourceMessage("user_delete_exception"));
            UserId userId = new UserId(id);
            this.userManagementService.delete(userId);
            model.addAttribute("success", getMessageSourceMessage("user_delete"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return userList(model);
        }
        return userList(model);
    }

    private String getMessageSourceMessage(String user_regist) {
        return messagesource.getMessage(user_regist, new String[]{}, Locale.JAPAN);
    }
}
