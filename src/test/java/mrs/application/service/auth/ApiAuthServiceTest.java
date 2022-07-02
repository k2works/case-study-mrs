package mrs.application.service.auth;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.infrastructure.security.jwt.payload.response.JwtResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("API認証・認可サービス")
public class ApiAuthServiceTest {
    @Autowired
    ApiAuthService apiAuthService;

    @Autowired
    TestDataFactory testDataFactory;

    @Nested
    class 利用者の登録 {
        @BeforeEach
        void setUp() {
            testDataFactory.setUp();
        }

        private User getUser(String userId) {
            return new User(userId, "山田", "太郎", "a234567Z", RoleName.一般);
        }

        @Test
        void 利用者を登録する() {
            User user = getUser("U999992");
            apiAuthService.registerUser(user.UserId(), user.Password(), user.Name(), user.RoleName());
            User result = apiAuthService.findByUserId(user.UserId());

            assertEquals(user, result);
        }
    }

    @Nested
    class 利用者の認証 {
        @MockBean
        ApiAuthService apiAuthServiceMock;

        @BeforeEach
        void setUp() {
            testDataFactory.setUp();
        }

        @Test
        void 利用者を認証する() {
            Mockito.when(apiAuthServiceMock.authenticateUser(Mockito.any(), Mockito.any()))
                    .thenReturn(new JwtResponse("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVMDAwMDA3IiwiaWF0IjoxNjU2NzMxODc3LCJleHAiOjE2NTY4MTgyNzd9.2JGYfw4c2P4EzCFFuCN7kf5fMihSXEVfLZSRnC5OOOn4vpPy9QewaVXTheUzsv16X8Lk1bpvcAyQYSUuKj0vJA", "U999999", List.of("一般")));
            User user = testDataFactory.User();
            JwtResponse result = apiAuthServiceMock.authenticateUser(user.UserId(), user.Password());

            assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVMDAwMDA3IiwiaWF0IjoxNjU2NzMxODc3LCJleHAiOjE2NTY4MTgyNzd9.2JGYfw4c2P4EzCFFuCN7kf5fMihSXEVfLZSRnC5OOOn4vpPy9QewaVXTheUzsv16X8Lk1bpvcAyQYSUuKj0vJA", result.getAccessToken());
            assertEquals(user.UserId().Value(), result.getUserId());
            assertEquals(List.of("一般"), result.getRoles());
        }
    }
}
