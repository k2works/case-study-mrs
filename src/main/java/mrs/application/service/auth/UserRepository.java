package mrs.application.service.auth;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;

import java.util.Optional;

/**
 * 会員レポジトリ
 */
public interface UserRepository {
    Optional<User> findByUserId(UserId userId);
}
