package mrs.domain.model.auth;

import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 会員
 */
public class User {
    private UserId userId;

    private String password;

    private UserName name;

    private RoleName roleName;

    private List<Reservation> reservations;

    public User(String userId, String firstName, String lastName, String password, RoleName roleName) {
        this.userId = new UserId(userId);
        this.name = new UserName(firstName, lastName);
        this.password = password;
        this.roleName = roleName;
    }

    @Deprecated
    public User() {
    }

    public UserId UserId() {
        return userId;
    }

    public String Password() {
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
}

