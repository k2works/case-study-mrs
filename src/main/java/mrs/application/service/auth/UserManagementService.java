package mrs.application.service.auth;

import mrs.domain.model.auth.user.PasswordException;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.infrastructure.datasource.Message;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 利用者の管理
 */
@Service
@Transactional
public class UserManagementService {
    private final UserRepository userRepository;

    private final Message message;

    private final PasswordEncoder encoder;

    public UserManagementService(UserRepository userRepository, Message message, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.message = message;
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
            throw new UserAlreadyRegistException(message.getMessageByKey("user_already_regist"));
        if (user.Password() == null || user.Password().Value().isEmpty())
            throw new PasswordException(message.getMessageByKey("user_no_password"));

        User registUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), encoder.encode(user.Password().Value()), user.RoleName());
        userRepository.save(registUser);
    }

    /**
     * 利用者を更新する
     */
    public void update(User user) {
        User existUser = userRepository.findByUserId(user.UserId()).orElseThrow(() -> new UserNotExistException(message.getMessageByKey("user_not_exist_id")));
        if (user.Password() != null && !user.Password().Value().isEmpty()) {
            User updateUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), encoder.encode(user.Password().Value()), user.RoleName());
            userRepository.save(updateUser);
        } else {
            User updateUser = new User(user.UserId().Value(), user.Name().FirstName(), user.Name().LastName(), existUser.Password().Value(), user.RoleName());
            userRepository.save(updateUser);
        }
    }

    /**
     * 利用者を削除する
     */
    public void delete(UserId userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotExistException(message.getMessageByKey("user_not_exist_id")));
        userRepository.delete(user);
    }

    /**
     * 利用者を検索する
     */
    public User findOne(UserId userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
