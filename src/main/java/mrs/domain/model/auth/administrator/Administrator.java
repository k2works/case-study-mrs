package mrs.domain.model.auth.administrator;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.domain.model.auth.user.UserName;
import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 管理者
 */
public class Administrator extends User {
    private final User user;

    private UserId userId;

    private String password;

    private UserName name;

    private RoleName roleName;

    private List<Reservation> reservations;

    public Administrator(User user) {
        this.user = user;
        this.userId = user.UserId();
        this.password = user.Password();
        this.name = user.Name();
        this.roleName = user.RoleName();
        this.reservations = user.getReservations();
    }

    @Override
    public UserId UserId() {
        return this.user.UserId();
    }

    @Override
    public String Password() {
        return this.user.Password();
    }

    @Override
    public UserName Name() {
        return this.user.Name();
    }

    @Override
    public RoleName RoleName() {
        return this.user.RoleName();
    }

    @Override
    public List<Reservation> getReservations() {
        return this.user.getReservations();
    }
}
