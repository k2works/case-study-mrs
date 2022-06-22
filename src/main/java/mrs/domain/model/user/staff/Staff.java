package mrs.domain.model.user.staff;

import mrs.domain.model.auth.user.*;
import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * スタッフ
 */
public class Staff extends User {
    private final User user;

    private UserId userId;

    private Password password;

    private UserName name;

    private RoleName roleName;

    private List<Reservation> reservations;

    public Staff(User user) {
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
    public Password Password() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Staff)) return false;
        Staff other = (Staff) obj;
        return this.user.equals(other.user);
    }

    @Override
    public int hashCode() {
        return this.user.hashCode();
    }

    @Override
    public String toString() {
        return "Staff{" +
                "user=" + user +
                ", userId=" + userId +
                ", password=" + password +
                ", name=" + name +
                ", roleName=" + roleName +
                ", reservations=" + reservations +
                '}';
    }
}
