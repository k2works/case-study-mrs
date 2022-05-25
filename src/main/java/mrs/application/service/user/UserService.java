package mrs.application.service.user;

import mrs.domain.model.user.User;
import mrs.domain.model.user.UserDetailsImpl;
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
    private final ServiceUserRepository userRepository;

    public UserService(ServiceUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 利用者を認証する
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(userId + " is not found.");
        }
        return new UserDetailsImpl(user.get());
    }
}
