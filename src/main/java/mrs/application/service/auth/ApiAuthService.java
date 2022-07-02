package mrs.application.service.auth;

import mrs.domain.model.auth.user.*;
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
 * API認証
 */
@Service
@Transactional
public class ApiAuthService {
    final AuthenticationManager authenticationManager;

    private final UserManagementService userManagementService;

    final JwtUtils jwtUtils;

    public ApiAuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserManagementService userManagementService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userManagementService = userManagementService;
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

        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }

    /**
     * 利用者を登録する
     */
    public void registerUser(UserId userId, Password password, UserName userName, RoleName role) {
        User newUser = new User(userId.Value(), userName.FirstName(), userName.LastName(), password.Value(), role);
        userManagementService.regist(newUser);
    }

    /**
     * 利用者を検索する
     */
    public User findByUserId(UserId userId) {
        return userManagementService.findOne(userId);
    }
}
