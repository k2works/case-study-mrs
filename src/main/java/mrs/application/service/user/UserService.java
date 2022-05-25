package mrs.application.service.user;

import mrs.domain.model.user.User;
import mrs.domain.model.user.UserDetailsImpl;
import mrs.domain.model.user.UserId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 利用者の認証
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 利用者を認証する
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserId(new UserId(userId));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(userId + " is not found.");
        }
        return new UserDetailsImpl(user.get());
    }
}
