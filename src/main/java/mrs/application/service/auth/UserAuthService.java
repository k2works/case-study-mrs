package mrs.application.service.auth;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.auth.user.UserId;
import mrs.domain.model.auth.user.administrator.Administrator;
import mrs.domain.model.auth.user.member.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 利用者の認証
 */
@Service
public class UserAuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
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
        if (user.get().RoleName().equals(RoleName.ADMIN))
            return new UserDetailsImpl(new Administrator(user.get()));
        if (user.get().RoleName().equals(RoleName.MEMBER))
            return new UserDetailsImpl(new Member(user.get()));
        else
            return new UserDetailsImpl(user.get());
    }
}
