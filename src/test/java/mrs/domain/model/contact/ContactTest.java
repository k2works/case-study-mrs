package mrs.domain.model.contact;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.user.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("問い合わせドメイン")
public class ContactTest {

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    void 問い合わせを生成できる() {
        Contact contact = new Contact(1, "問い合わせ1");

        assertEquals(new ContactId("1"), contact.ContactId());
    }

    @Test
    void 問い合わせ一覧を生成できる() {
        List<Contact> list = List.of(
                new Contact(1, "問い合わせ1"),
                new Contact(2, "問い合わせ2")
        );
        ContactList contactList = new ContactList(list);

        assertEquals(2, contactList.size());
        assertEquals(list, contactList.asList());
    }

    @Test
    void 問い合わせした会員情報を保持している() {
        User user = newUser();
        Member member = new Member(user);
        Contact contact = new Contact(1, "問い合わせ1", member);

        assertEquals(member, contact.Member());
    }
}
