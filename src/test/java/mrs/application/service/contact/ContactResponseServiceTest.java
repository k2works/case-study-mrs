package mrs.application.service.contact;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactList;
import mrs.domain.model.user.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("問い合わせサービス")
public class ContactResponseServiceTest {

    @Autowired
    ContactRegistService contactRegistService;
    @Autowired
    ContactResponseService contactResponseService;

    @Autowired
    TestDataFactory testDataFactory;

    @BeforeEach
    void setUp() {
        testDataFactory.setUp();
    }

    @Nested
    class 問い合わせ {
        @Test
        void 問い合わせをする() {
            UserId userId = new UserId("U999999");
            User user = testDataFactory.getUserMapper().select(userId);
            Member member = new Member(user);
            Contact contact = new Contact(1, "問い合わせ", member);
            contactRegistService.create(contact);
            Contact result = contactResponseService.findByContactId(contact.ContactId());

            assertEquals(contact.ContactId(), result.ContactId());
            assertEquals(contact.Details(), result.Details());
            assertEquals(contact.Member(), result.Member());
        }

    }

    @Nested
    class 問い合わせ対応 {
        @Test
        void 問い合わせを確認する() {
            UserId userId = new UserId("U999999");
            User user = testDataFactory.getUserMapper().select(userId);
            Member member = new Member(user);
            IntStream.rangeClosed(1, 10).forEach(i -> contactRegistService.create(new Contact(i, "問い合わせ", member)));

            ContactList contacts = contactResponseService.findAll();

            assertEquals(10, contacts.size());
        }
    }

}
