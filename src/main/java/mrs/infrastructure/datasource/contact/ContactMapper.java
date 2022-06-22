package mrs.infrastructure.datasource.contact;

import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper {
    void insert(Contact contact);

    Contact select(ContactId contactId);

    void update(Contact update);

    void delete(ContactId contactId);
}
