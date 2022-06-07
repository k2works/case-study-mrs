package mrs.application.service.auth;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;

import java.util.List;
import java.util.Optional;

/**
 * 会員レポジトリ
 */
public interface UserRepository {
    Optional<User> findByUserId(UserId userId);

    List<User> findAll();

    void save(User user);

    void delete(User user);
}
