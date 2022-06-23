package mrs.presentation.contact;

import com.github.pagehelper.PageInfo;
import mrs.application.scenario.ContactManagementScenario;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactList;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 問い合わせ一覧画面
 */
@Controller
@RequestMapping("contacts")
public class ContactResponseController {
    private final ContactManagementScenario contactManagementScenario;
    private final Message message;

    public ContactResponseController(ContactManagementScenario contactManagementScenario, Message message) {
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

    @GetMapping
    String listContacts(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        ContactList contacts = contactManagementScenario.findAll();
        PageInfo<Contact> pageInfo = new PageInfo<>(contacts.asList());
        model.addAttribute("contacts", contacts.asList());
        model.addAttribute("pageInfo", pageInfo);
        return "contact/listContacts";
    }
}
