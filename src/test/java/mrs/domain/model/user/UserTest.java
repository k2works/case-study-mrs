package mrs.domain.model.user;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.administrator.Administrator;
import mrs.domain.model.auth.user.member.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void 利用者を生成できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.MEMBER);

        assertEquals("1", user.UserId().Value());
        assertEquals("テスト", user.Name().FirstName());
        assertEquals("太郎", user.Name().LastName());
        assertEquals("password", user.Password().Value());
        assertEquals(RoleName.MEMBER, user.RoleName());
    }

    @Test
    public void 会員を生成できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.MEMBER);
        Member member = new Member(user);

        assertEquals("1", member.UserId().Value());
        assertEquals("テスト", member.Name().FirstName());
        assertEquals("太郎", member.Name().LastName());
        assertEquals("password", member.Password().Value());
        assertEquals(RoleName.MEMBER, member.RoleName());
    }

    @Test
    public void 管理者を生成できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.MEMBER);
        Administrator administrator = new Administrator(user);

        assertEquals("1", administrator.UserId().Value());
        assertEquals("テスト", administrator.Name().FirstName());
        assertEquals("太郎", administrator.Name().LastName());
        assertEquals("password", administrator.Password().Value());
        assertEquals(RoleName.MEMBER, administrator.RoleName());
    }
}
