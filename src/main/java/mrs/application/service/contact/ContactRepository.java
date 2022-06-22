package mrs.application.service.contact;

import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactId;
import mrs.domain.model.contact.ContactList;

public interface ContactRepository {
    void save(Contact contact);

    Contact findByContactId(ContactId contactId);

    ContactList findAll();
}
