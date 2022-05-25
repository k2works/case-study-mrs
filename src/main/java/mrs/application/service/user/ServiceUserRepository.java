package mrs.application.service.user;

import mrs.domain.model.user.User;

import java.util.Optional;

/**
 * 会員レポジトリ
 */
public interface ServiceUserRepository {
    Optional<User> findByUserId(String userId);
}
