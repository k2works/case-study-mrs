package mrs.application.service.auth;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> result = userRepository.findByUserId(user.UserId());
        if (result.isPresent())
            throw new IllegalArgumentException("すでに存在する利用者番号です");
        if (user.Password() == null || user.Password().isEmpty())
            throw new IllegalArgumentException("パスワードを入力してください");

        userRepository.save(user);
    }

    /**
     * 利用者を更新する
     */
    public void update(User user) {
        User existUser = userRepository.findByUserId(user.UserId()).orElseThrow(() -> new IllegalArgumentException("存在しない利用者番号です"));
        if (user.Password() != null && !user.Password().isEmpty()) {
            userRepository.save(user);
        } else {
            User updateUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), existUser.Password(), user.RoleName());
            userRepository.save(updateUser);
        }
    }

    /**
     * 利用者を削除する
     */
    public void delete(UserId userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("存在しない利用者番号です"));
        userRepository.delete(user);
    }

    /**
     * 利用者を検索する
     */
    public User findOne(UserId userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
