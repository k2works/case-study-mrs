package mrs.infrastructure.datasource.service_user;

import mrs.domain.model.service_user.RoleName;
import mrs.domain.model.service_user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserDataSourceTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDataSource userDataSource;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
    }

    @Test
    public void 会員を取得できる() {
        userMapper.insert(new User("5", "テスト", "太郎", "password", RoleName.USER));

        Optional<User> result = userDataSource.findByUserId("5");
        assert (result.isPresent());
        assert (result.get().getUserId().equals("5"));
    }
}
