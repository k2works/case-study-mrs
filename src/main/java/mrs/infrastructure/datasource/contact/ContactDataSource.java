package mrs.infrastructure.datasource.contact;

import mrs.application.service.contact.ContactRepository;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactId;
import mrs.domain.model.contact.ContactList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDataSource implements ContactRepository {
   ContactMapper contactMapper;

   public ContactDataSource(ContactMapper contactMapper) {
      this.contactMapper = contactMapper;
   }

   @Override
   public void save(Contact contact) {
      contactMapper.insert(contact);
   }

   @Override
   public Contact findByContactId(ContactId contactId) {
      return contactMapper.select(contactId);
   }

   @Override
   public ContactList findAll() {
      List<Contact> contacts = contactMapper.selectAll();
      return new ContactList(contacts);
   }
}

