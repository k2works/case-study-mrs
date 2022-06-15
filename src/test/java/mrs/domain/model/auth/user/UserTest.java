package mrs.domain.model.auth.user;

import mrs.domain.model.auth.user.administrator.Administrator;
import mrs.domain.model.auth.user.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("認証・認可ドメイン")
public class UserTest {

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.MEMBER);
    }

    @Nested
    class 利用者 {
        @Test
        public void 利用者を生成できる() {
            User user = newUser();

            assertEquals("U999999", user.UserId().Value());
            assertEquals("テスト", user.Name().FirstName());
            assertEquals("太郎", user.Name().LastName());
            assertEquals("a234567Z", user.Password().Value());
            assertEquals(RoleName.MEMBER, user.RoleName());
        }

        @Test
        public void 利用者番号が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User(null, "テスト", "太郎", "password", RoleName.MEMBER));
        }

        @Test
        public void 名前が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("U999999", null, "太郎", "password", RoleName.MEMBER));
            assertThrows(UserException.class, () -> new User("U999999", "テスト", null, "password", RoleName.MEMBER));
        }

        @Test
        public void 役割が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("U999999", "テスト", "太郎", "password", null));
        }

        @Test
        public void パスワードが未入力の場合は空の値を設定する() {
            User user = new User("U999999", "テスト", "太郎", null, RoleName.MEMBER);
            assertTrue(user.Password().Value().isEmpty());
        }

        @Test
        public void パスワードは少なくとも8文字以上であること() {
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "pass", RoleName.MEMBER));
        }

        @Test
        public void パスワードは小文字大文字数字を含むこと() {
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "12345678", RoleName.MEMBER));
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "a2345678", RoleName.MEMBER));
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "A2345678", RoleName.MEMBER));
        }

        @Test
        public void 利用者番号は先頭の一文字目がUで始まる6桁の数字である() {
            assertThrows(UserIdException.class, () -> new User("1", "テスト", "太郎", "password", RoleName.MEMBER));
            assertThrows(UserIdException.class, () -> new User("X123456", "テスト", "太郎", "password", RoleName.MEMBER));
            assertThrows(UserIdException.class, () -> new User("U12345", "テスト", "太郎", "password", RoleName.MEMBER));
            assertThrows(UserIdException.class, () -> new User("Uabcdef", "テスト", "太郎", "password", RoleName.MEMBER));
        }
    }

    @Nested
    class 会員 {
        @Test
        public void 会員を生成できる() {
            User user = newUser();
            Member member = new Member(user);

            assertEquals("U999999", member.UserId().Value());
            assertEquals("テスト", member.Name().FirstName());
            assertEquals("太郎", member.Name().LastName());
            assertEquals("a234567Z", member.Password().Value());
            assertEquals(RoleName.MEMBER, member.RoleName());
        }

    }

    @Nested
    class 管理者 {
        @Test
        public void 管理者を生成できる() {
            User user = newUser();
            Administrator administrator = new Administrator(user);

            assertEquals("U999999", administrator.UserId().Value());
            assertEquals("テスト", administrator.Name().FirstName());
            assertEquals("太郎", administrator.Name().LastName());
            assertEquals("a234567Z", administrator.Password().Value());
            assertEquals(RoleName.MEMBER, administrator.RoleName());
        }
    }
}
