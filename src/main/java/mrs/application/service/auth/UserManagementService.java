package mrs.application.service.auth;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 利用者の管理
 */
@Service
public class UserManagementService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserManagementService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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

        User registUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), encoder.encode(user.Password()), user.RoleName());
        userRepository.save(registUser);
    }

    /**
     * 利用者を更新する
     */
    public void update(User user) {
        User existUser = userRepository.findByUserId(user.UserId()).orElseThrow(() -> new IllegalArgumentException("存在しない利用者番号です"));
        if (user.Password() != null && !user.Password().isEmpty()) {
            User updateUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), encoder.encode(user.Password()), user.RoleName());
            userRepository.save(updateUser);
        } else {
            User updateUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), existUser.Password(), user.RoleName());
            userRepository.save(updateUser);
        }
    }

    /**
     * 利用者を削除する
     */
    @PreAuthorize("#userId != principal.user.UserId")
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
