package mrs.application.service.contact;

import mrs.domain.model.contact.Contact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 問い合わせの実施
 */
@Service
@Transactional
public class ContactRegistService {
    final ContactRepository contactRepository;

    public ContactRegistService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * 問い合わせをする
     */
    public void create(Contact contact) {
        contactRepository.save(contact);
    }
}
