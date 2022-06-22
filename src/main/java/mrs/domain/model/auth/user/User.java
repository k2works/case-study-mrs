package mrs.domain.model.auth.user;

import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 利用者
 */
public class User {
    private UserId userId;

    private Password password;

    private UserName name;

    private RoleName roleName;

    private List<Reservation> reservations;

    public User(String userId, String firstName, String lastName, String password, RoleName roleName) {
        if (userId == null) throw new UserException("利用者番号が未入力です");
        if (firstName == null || lastName == null) throw new UserException("名前が未入力です");
        if (roleName == null) throw new UserException("役割が未入力です");

        this.userId = new UserId(userId);
        this.name = new UserName(firstName, lastName);
        this.password = new Password(password);
        this.roleName = roleName;
    }

    @Deprecated
    public User() {
    }

    public UserId UserId() {
        return userId;
    }

    public Password Password() {
        return password;
    }

    public UserName Name() {
        return name;
    }

    public RoleName RoleName() {
        return roleName;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", roleName=" + roleName + "]";
    }
}

