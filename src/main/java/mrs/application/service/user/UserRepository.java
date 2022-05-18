package mrs.application.service.user;

import mrs.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 利用者リポジトリ
 */
public interface UserRepository extends JpaRepository<User, String> {
}
