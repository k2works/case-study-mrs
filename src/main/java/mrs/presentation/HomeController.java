package mrs.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/content")
    public String contents() {
        return "content";
    }

    @GetMapping("/accessDenied")
    private String accessDenied() {
        return "accessDenied";
    }
}
