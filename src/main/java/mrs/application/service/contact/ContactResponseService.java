package mrs.application.service.contact;

import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactId;
import mrs.domain.model.contact.ContactList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 問い合わせの対応
 */
@Service
@Transactional
public class ContactResponseService {
    final ContactRepository contactRepository;

    public ContactResponseService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * 問い合わせを取得する
     */
    public Contact findByContactId(ContactId contactId) {
        return contactRepository.findByContactId(contactId);
    }

    /**
     * 問い合わせ一覧を確認する
     */
    public ContactList findAll() {
        return contactRepository.findAll();
    }
}
