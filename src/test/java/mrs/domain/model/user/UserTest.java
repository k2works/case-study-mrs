package mrs.domain.model.user;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserException;
import mrs.domain.model.auth.user.administrator.Administrator;
import mrs.domain.model.auth.user.member.Member;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Nested
    class 利用者 {
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
        public void 利用者番号が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User(null, "テスト", "太郎", "password", RoleName.MEMBER));
        }

        @Test
        public void 名前が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("1", null, "太郎", "password", RoleName.MEMBER));
            assertThrows(UserException.class, () -> new User("1", "テスト", null, "password", RoleName.MEMBER));
        }

        @Test
        public void 役割が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("1", "テスト", "太郎", "password", null));
        }

        @Test
        public void パスワードが未入力の場合は空の値を設定する() {
            User user = new User("1", "テスト", "太郎", null, RoleName.MEMBER);
            assertTrue(user.Password().Value().isEmpty());
        }
    }

    @Nested
    class 会員 {
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

    }

    @Nested
    class 管理者 {
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
}
