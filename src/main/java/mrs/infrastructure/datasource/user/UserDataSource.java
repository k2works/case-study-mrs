package mrs.infrastructure.datasource.user;

import mrs.application.service.user.UserRepository;
import mrs.domain.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDataSource implements UserRepository {
    UserMapper userMapper;

    public UserDataSource(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(userMapper.select(userId));
    }
}
