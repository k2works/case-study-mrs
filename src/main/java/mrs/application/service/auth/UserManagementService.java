package mrs.application.service.auth;

import mrs.domain.model.auth.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 利用者の管理
 */
@Service
public class UserManagementService {
    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 利用者を確認する
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 利用者を登録する
     */
    public void regist(User user) {
        userRepository.save(user);
    }

    /**
     * 利用者を更新する
     */
    public void update(User user) {
        userRepository.save(user);
    }

    /**
     * 利用者を削除する
     */
    public void delete(User user) {
        userRepository.delete(user);
    }
}
