package mrs.presentation.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 認証画面
 */
@Controller("利用者の認証")
public class LoginController {
    @GetMapping("loginForm")
    String loginForm() {
        return "login/loginForm";
    }
}
