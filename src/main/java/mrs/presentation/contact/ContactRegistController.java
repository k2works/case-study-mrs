package mrs.presentation.contact;

import mrs.application.scenario.ContactManagementScenario;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.auth.user.UserName;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.user.gust.Gust;
import mrs.domain.model.user.member.Member;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 問い合わせ画面
 */
@Controller
@RequestMapping("contacts")
public class ContactRegistController {
    private final ContactManagementScenario contactManagementScenario;
    private final Message message;

    public ContactRegistController(ContactManagementScenario contactManagementScenario, Message message) {
        this.contactManagementScenario = contactManagementScenario;
        this.message = message;
    }

    @ModelAttribute
    public ContactForm setUpForm() {
        return new ContactForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("regist")
    String registContact(Model model) {
        return "contact/contactForm";
    }

    @PostMapping(params = "regist")
    String createContact(Model model, @Validated ContactForm form, BindingResult result, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return registContact(model);
        }
        try {
            Contact contact = new Contact(form.getDetails(), new Member(userDetails.getUser()));
            contactManagementScenario.create(contact);
            model.addAttribute("success", message.getMessageByKey("contact_regist"));
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            return registContact(model);
        }
        return registContact(model);
    }

    @GetMapping("gust/regist")
    String registGustContact(Model model) {
        return "contact/contactGustForm";
    }

    @PostMapping(params = "regist", path = "gust")
    String createGustContact(Model model, @Validated ContactForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return registGustContact(model);
        }
        try {
            Contact contact = new Contact(form.getDetails(), new Gust(new UserName("", "")));
            contactManagementScenario.create(contact);
            model.addAttribute("success", message.getMessageByKey("contact_regist"));
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            return registGustContact(model);
        }
        return registGustContact(model);
    }
}
