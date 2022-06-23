package mrs.application.scenario;

import mrs.application.service.contact.ContactRegistService;
import mrs.application.service.contact.ContactService;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactList;
import org.springframework.stereotype.Service;

/**
 * 問い合わせ管理シナリオ
 */
@Service
public class ContactManagementScenario {
    private final ContactRegistService contactRegistService;
    private final ContactService contactService;

    public ContactManagementScenario(ContactRegistService contactRegistService, ContactService contactService) {
        this.contactRegistService = contactRegistService;
        this.contactService = contactService;
    }

    /**
     * 問い合わせをする
     */
    public void create(Contact contact) {
        contactRegistService.create(contact);
    }

    /**
     * 問い合わせ一覧を確認する
     */
    public ContactList findAll() {
        return contactService.findAll();
    }
}
