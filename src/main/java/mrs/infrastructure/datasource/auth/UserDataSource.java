package mrs.infrastructure.datasource.auth;

import mrs.application.service.auth.UserRepository;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<User> findAll() {
        return this.userMapper.selectAll();
    }

    @Override
    public void save(User user) {
        User result = this.userMapper.select(user.UserId());
        if (result == null) {
            this.userMapper.insert(user);
        } else {
            this.userMapper.update(user);
        }
    }

    @Override
    public void delete(User user) {
        this.userMapper.delete(user.UserId());
    }
}
