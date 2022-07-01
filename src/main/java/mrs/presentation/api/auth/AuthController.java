package mrs.presentation.api.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.service.auth.UserRepository;
import mrs.domain.model.auth.user.*;
import mrs.infrastructure.security.jwt.JwtUtils;
import mrs.infrastructure.security.jwt.payload.request.LoginRequest;
import mrs.infrastructure.security.jwt.payload.request.SignupRequest;
import mrs.infrastructure.security.jwt.payload.response.JwtResponse;
import mrs.infrastructure.security.jwt.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "JWTAuth", description = "JWT認証テスト")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "ユーザー認証", description = "データベースに登録されているユーザーを認証する")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> user = userRepository.findByUserId(new UserId(loginRequest.getUserId()));
            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: User is not exist"));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).toList();

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "ユーザー登録", description = "ユーザーを新規登録する")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            Optional<User> user = userRepository.findByUserId(new UserId(signupRequest.getUserId()));
            if (user.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: UserId is already taken"));
            }

            UserId userId = new UserId(signupRequest.getUserId());
            String password = passwordEncoder.encode(signupRequest.getPassword());
            UserName userName = new UserName(signupRequest.getFirstName(), signupRequest.getLastName());
            String role = signupRequest.getRole();
            RoleName roleName;
            if (role == null) {
                roleName = RoleName.一般;
            } else {
                roleName = RoleName.valueOf(role);
            }
            User newUser = new User(userId.Value(), userName.FirstName(), userName.LastName(), password, roleName);
            userRepository.save(newUser);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
