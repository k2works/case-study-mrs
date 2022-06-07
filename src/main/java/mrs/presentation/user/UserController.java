package mrs.presentation.user;

import mrs.application.service.auth.UserManagementService;
import mrs.domain.model.auth.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 利用者一覧画面
 */
@Controller("利用者の管理")
@RequestMapping("/users")
public class UserController {
    private final UserManagementService userManagementService;

    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping
    String userList(Model model) {
        List<User> users = userManagementService.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @PostMapping
    void userCreateUpdate() {
        User user = new User();
        if (user.UserId() == null) {
            this.userManagementService.regist(user);
        } else {
            this.userManagementService.update(user);
        }
    }

    @DeleteMapping
    void userDelete() {
        User user = new User();
        this.userManagementService.delete(user);
    }
}
