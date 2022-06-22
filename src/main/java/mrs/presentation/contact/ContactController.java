package mrs.presentation.contact;

import com.github.pagehelper.PageInfo;
import mrs.application.service.contact.ContactService;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactList;
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

@Controller
@RequestMapping("contacts")
public class ContactController {
    private final ContactService contactService;

    private final Message message;

    public ContactController(ContactService contactService, Message message) {
        this.contactService = contactService;
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
            Contact contact = new Contact(1, form.getDetails(), new Member(userDetails.getUser()));
            contactService.create(contact);
            model.addAttribute("success", message.getMessageByKey("contact_regist"));
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            return registContact(model);
        }
        return registContact(model);
    }

    @GetMapping
    String listContacts(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        ContactList contacts = contactService.findAll();
        PageInfo<Contact> pageInfo = new PageInfo<>(contacts.asList());
        model.addAttribute("contacts", contacts.asList());
        model.addAttribute("pageInfo", pageInfo);
        return "contact/listContacts";
    }
}
