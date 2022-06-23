package mrs.domain.model.contact;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.user.gust.Gust;
import mrs.domain.model.user.member.Member;

import java.util.UUID;

/**
 * 問い合わせ
 */
public class Contact {
    ContactId contactId;
    String details;

    Member member;

    User user;

    @Deprecated
    public Contact() {
    }

    public Contact(Integer contactId, String details) {
        this.contactId = new ContactId(contactId.toString());
        this.details = details;
    }

    public Contact(Integer contactId, String details, Member member) {
        this.contactId = new ContactId(contactId.toString());
        this.details = details;
        this.member = member;
        this.user = member;
    }

    public Contact(String details, Member member) {
        this.contactId = new ContactId(UUID.randomUUID().toString());
        this.details = details;
        this.member = member;
        this.user = member;
    }

    public Contact(String details, Gust gust) {
        this.contactId = new ContactId(UUID.randomUUID().toString());
        this.details = details;
        this.member = new Member(gust);
        this.user = gust;
    }

    public ContactId ContactId() {
        return contactId;
    }

    public String Details() {
        return details;
    }

    public ContactPerson Member() {
        return new Member(user);
    }
}
