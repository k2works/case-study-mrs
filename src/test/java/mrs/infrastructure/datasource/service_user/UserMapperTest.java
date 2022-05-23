package mrs.infrastructure.datasource.service_user;

import mrs.domain.model.service_user.RoleName;
import mrs.domain.model.service_user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
    }

    @Test
    public void ユーザーが登録できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));

        User user = userMapper.select("1");
        assert (user.getUserId().equals("1"));
        assert (user.getFirstName().equals("テスト"));
        assert (user.getLastName().equals("太郎"));
        assert (user.getPassword().equals("password"));
        assert (user.getRoleName().equals(RoleName.USER));
    }

    @Test
    public void 複数のユーザーを登録できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));
        userMapper.insert(new User("2", "テスト", "太郎", "password", RoleName.USER));

        List<User> users = userMapper.selectAllJoin();
        assert (users.size() == 2);
    }

    @Test
    public void ユーザーを更新できる() {
        userMapper.insert(new User("3", "テスト", "太郎", "password", RoleName.USER));

        User user = userMapper.select("3");
        user.setFirstName("更新1");
        user.setLastName("更新2");
        user.setPassword("updated");
        user.setRoleName(RoleName.ADMIN);
        userMapper.update(user);

        User updatedUser = userMapper.select("3");
        assert (updatedUser.getFirstName().equals("更新1"));
        assert (updatedUser.getLastName().equals("更新2"));
        assert (updatedUser.getPassword().equals("updated"));
        assert (updatedUser.getRoleName().equals(RoleName.ADMIN));
    }

    @Test
    public void ユーザーを削除できる() {
        userMapper.insert(new User("4", "テスト", "太郎", "password", RoleName.USER));

        userMapper.delete("4");
        User user = userMapper.select("4");
        assert (user == null);
    }
}
