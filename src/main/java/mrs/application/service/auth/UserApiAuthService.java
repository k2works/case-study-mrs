package mrs.application.service.auth;

import mrs.domain.model.auth.user.Password;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.auth.user.UserId;
import mrs.infrastructure.security.jwt.JwtUtils;
import mrs.infrastructure.security.jwt.payload.response.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 利用者のAPI認証
 */
@Service
@Transactional
public class UserApiAuthService {
    final AuthenticationManager authenticationManager;

    final JwtUtils jwtUtils;

    public UserApiAuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 利用者を認証する
     */
    public JwtResponse authenticateUser(UserId userId, Password password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId.Value(), password.Value())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return new JwtResponse(jwt, userDetails.getUsername(), roles, userDetails.getUser());
    }
}
