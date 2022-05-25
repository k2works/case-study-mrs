package mrs.application.service.user;

import mrs.domain.model.user.ReservationUserDetails;
import mrs.domain.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final ServiceUserRepository userRepository;

    public UserService(ServiceUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(userId + " is not found.");
        }
        return new ReservationUserDetails(user.get());
    }
}
