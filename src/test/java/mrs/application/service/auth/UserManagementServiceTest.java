package mrs.application.service.auth;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest
@DisplayName("認証・認可サービス")
public class UserManagementServiceTest {
    @Autowired
    UserManagementService userManagementService;

    @Autowired
    TestDataFactory testDataFactory;

    private User getUser(String userId) {
        return new User(userId, "山田", "太郎", "a234567Z", RoleName.一般);
    }

    @Nested
    class 利用者の管理 {
        @BeforeEach
        void setUp() {
            testDataFactory.setUp();
        }

        @Test
        @WithMockUser
        void 利用者を確認する() {
            List<User> result = userManagementService.findAll();

            assertEquals(1, result.size());
        }

        @Test
        @WithMockUser
        void 利用者を更新する() {
            User user = getUser("U999992");
            userManagementService.regist(user);
            User registUser = userManagementService.findOne(user.UserId());
            User updateUser = new User(user.UserId().Value(), "山田", "次郎", "A234567z", RoleName.一般);
            userManagementService.update(updateUser);

            User result = userManagementService.findOne(user.UserId());
            assertNotEquals(registUser.Name().FullName(), result.Name().FullName());
        }

        @Test
        @WithMockUser
        void 利用者を削除する() {
            User user = getUser("U999992");
            userManagementService.regist(user);
            User registUser = userManagementService.findOne(user.UserId());
            userManagementService.delete(registUser.UserId());
            User result = userManagementService.findOne(user.UserId());

            assertNull(result);
        }

        @Nested
        class 利用者を登録する {
            @Test
            @WithMockUser
            void 新規利用者番号を使って登録する() {
                User user = getUser("U999992");

                userManagementService.regist(user);

                User result = userManagementService.findOne(user.UserId());
                assertEquals(user.UserId().Value(), result.UserId().Value());
            }

            @Test
            @WithMockUser
            void すでに存在する利用者番号を使って登録する() {
                User user = getUser("U999992");
                userManagementService.regist(user);

                User user2 = new User(user.UserId().Value(), "山田", "次郎", "a234567Z", RoleName.一般);
                assertThrows(UserAlreadyRegistException.class, () -> userManagementService.regist(user2));
            }
        }

    }

}
