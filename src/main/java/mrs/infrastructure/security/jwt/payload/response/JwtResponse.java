package mrs.infrastructure.security.jwt.payload.response;

import mrs.domain.model.auth.user.User;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String userId;
    private List<String> roles;

    private User userInfo;

    public JwtResponse(String accessToken, String userId, List<String> roles, User user) {
        this.token = accessToken;
        this.userId = userId;
        this.roles = roles;
        this.userInfo = new User(user.UserId(), user.Name(), user.RoleName());
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public User getUserInfo() {
        return userInfo;
    }
}
