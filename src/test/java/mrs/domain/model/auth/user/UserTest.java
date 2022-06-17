package mrs.domain.model.auth.user;

import mrs.domain.model.user.member.Member;
import mrs.domain.model.user.staff.Staff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("認証・認可ドメイン")
public class UserTest {

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
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
            assertEquals(RoleName.一般, user.RoleName());
        }

        @Test
        public void 利用者番号が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User(null, "テスト", "太郎", "password", RoleName.一般));
        }

        @Test
        public void 名前が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("U999999", null, "太郎", "password", RoleName.一般));
            assertThrows(UserException.class, () -> new User("U999999", "テスト", null, "password", RoleName.一般));
        }

        @Test
        public void 役割が未入力の場合は生成できない() {
            assertThrows(UserException.class, () -> new User("U999999", "テスト", "太郎", "password", null));
        }

        @Test
        public void パスワードが未入力の場合は空の値を設定する() {
            User user = new User("U999999", "テスト", "太郎", null, RoleName.一般);
            assertTrue(user.Password().Value().isEmpty());
        }

        @Test
        public void パスワードは少なくとも8文字以上であること() {
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "pass", RoleName.一般));
        }

        @Test
        public void パスワードは小文字大文字数字を含むこと() {
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "12345678", RoleName.一般));
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "a2345678", RoleName.一般));
            assertThrows(PasswordException.class, () -> new User("U999999", "テスト", "太郎", "A2345678", RoleName.一般));
        }

        @Test
        public void 利用者番号は先頭の一文字目がUで始まる6桁の数字である() {
            assertThrows(UserIdException.class, () -> new User("1", "テスト", "太郎", "password", RoleName.一般));
            assertThrows(UserIdException.class, () -> new User("X123456", "テスト", "太郎", "password", RoleName.一般));
            assertThrows(UserIdException.class, () -> new User("U12345", "テスト", "太郎", "password", RoleName.一般));
            assertThrows(UserIdException.class, () -> new User("Uabcdef", "テスト", "太郎", "password", RoleName.一般));
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
            assertEquals(RoleName.一般, member.RoleName());
        }

    }

    @Nested
    class 管理者 {
        @Test
        public void 管理者を生成できる() {
            User user = newUser();
            Staff staff = new Staff(user);

            assertEquals("U999999", staff.UserId().Value());
            assertEquals("テスト", staff.Name().FirstName());
            assertEquals("太郎", staff.Name().LastName());
            assertEquals("a234567Z", staff.Password().Value());
            assertEquals(RoleName.一般, staff.RoleName());
        }
    }
}
