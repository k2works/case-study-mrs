package mrs.infrastructure.datasource.contact;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.user.member.Member;
import mrs.infrastructure.datasource.auth.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@DisplayName("問い合わせエンティティ")
public class ContactMapperTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ContactMapper contactMapper;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
    }

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    void 問い合わせを登録できる() {
        User user = newUser();
        userMapper.insert(user);
        Member member = new Member(user);
        Contact contact = new Contact(1, "問い合わせ1", member);
        contactMapper.insert(contact);

        Contact result = contactMapper.select(contact.ContactId());
        assertEquals(result.ContactId(), contact.ContactId());
        assertEquals(result.Details(), contact.Details());
        assertEquals(result.Member(), contact.Member());
    }

    @Test
    void 問い合わせを更新できる() {
        User user = newUser();
        userMapper.insert(user);
        Member member = new Member(user);
        Contact contact = new Contact(1, "問い合わせ1", member);
        contactMapper.insert(contact);
        Contact update = new Contact(1, "問い合わせ更新", member);
        contactMapper.update(update);
        Contact result = contactMapper.select(contact.ContactId());

        assertEquals(result.ContactId(), update.ContactId());
        assertEquals(result.Details(), update.Details());
        assertEquals(result.Member(), update.Member());
    }

    @Test
    void 問い合わせを削除できる() {
        User user = newUser();
        userMapper.insert(user);
        Member member = new Member(user);
        Contact contact = new Contact(1, "問い合わせ1", member);
        contactMapper.insert(contact);
        contactMapper.delete(contact.ContactId());
        Contact result = contactMapper.select(contact.ContactId());

        assertEquals(null, result);
    }
}
