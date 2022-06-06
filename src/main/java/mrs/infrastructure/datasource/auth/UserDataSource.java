package mrs.infrastructure.datasource.auth;

import mrs.application.service.auth.UserRepository;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDataSource implements UserRepository {
    UserMapper userMapper;

    public UserDataSource(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByUserId(UserId userId) {
        return Optional.ofNullable(userMapper.select(userId));
    }
}
