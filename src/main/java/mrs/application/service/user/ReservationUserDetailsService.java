package mrs.application.service.user;

import mrs.domain.model.user.ReservationUserDetails;
import mrs.domain.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 利用者の認証
 */
@Service
public class ReservationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ReservationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 利用者を認証する
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        return new ReservationUserDetails(user.get());
    }
}
