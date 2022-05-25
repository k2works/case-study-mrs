package mrs.domain.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 認証済み利用者
 */
public class UserDetailsImpl implements UserDetails {
    private final mrs.domain.model.user.User user;

    public UserDetailsImpl(mrs.domain.model.user.User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_" + this.user.RoleName().name());
    }

    @Override
    public String getPassword() {
        return this.user.Password();
    }

    @Override
    public String getUsername() {
        return this.user.UserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
