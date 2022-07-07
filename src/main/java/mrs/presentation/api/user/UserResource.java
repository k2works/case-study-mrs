package mrs.presentation.api.user;

import io.swagger.v3.oas.annotations.media.Schema;
import mrs.domain.model.auth.user.RoleName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "利用者")
public class UserResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private RoleName roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
