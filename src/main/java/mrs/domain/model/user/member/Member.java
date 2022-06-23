package mrs.domain.model.user.member;

import mrs.domain.model.auth.user.*;
import mrs.domain.model.contact.ContactPerson;
import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 会員
 */
public class Member extends User implements ContactPerson {
    private User user;

    private UserId userId;

    private Password password;

    private UserName name;

    private RoleName roleName;

    private List<Reservation> reservations;

    private MemberId memberId;

    @Deprecated
    public Member() {
    }

    public Member(User user) {
        this.user = user;
        this.userId = user.UserId();
        this.password = user.Password();
        this.name = user.Name();
        this.roleName = user.RoleName();
        this.reservations = user.getReservations();
        this.memberId = new MemberId(user.UserId().Value());
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

    public MemberId MemberId() {
        return this.memberId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Member)) return false;
        Member other = (Member) obj;
        return this.userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId=" + userId +
                ", password=" + password +
                ", name=" + name +
                ", roleName=" + roleName +
                ", reservations=" + reservations +
                ", memberId=" + memberId +
                '}';
    }
}
