package mrs.application.service.service_user;

import mrs.domain.model.service_user.User;

import java.util.Optional;

/**
 * 会員レポジトリ
 */
public interface ServiceUserRepository {
    Optional<User> findByUserId(String userId);
}
