package mrs.application.service.user;

import mrs.domain.model.user.User;
import mrs.domain.model.user.UserId;

import java.util.Optional;

/**
 * 会員レポジトリ
 */
public interface UserRepository {
    Optional<User> findByUserId(UserId userId);
}
