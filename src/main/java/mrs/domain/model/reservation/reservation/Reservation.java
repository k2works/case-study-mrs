package mrs.domain.model.reservation.reservation;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;

import java.time.LocalTime;
import java.util.Objects;

/**
 * 予約
 */
public class Reservation {
    private ReservationId reservationId;

    private ReservedTime reservedTime;

    private ReservableRoom reservableRoom;

    private User user;

    @Deprecated
    public Reservation() {
    }

    ;

    public Reservation(Integer reservationId, LocalTime startTime, LocalTime endTime, ReservableRoomId reservableRoomId, User user) {
        this.reservationId = new ReservationId(reservationId);
        this.reservedTime = new ReservedTime(startTime, endTime);
        this.reservableRoom = new ReservableRoom(reservableRoomId);
        this.user = user;
    }

    public boolean overlap(Reservation target) {
        if (!Objects.equals(reservableRoom.ReservableRoomId(), target.reservableRoom.ReservableRoomId())) {
            return false;
        }
        return reservedTime.overlap(target.reservedTime);
    }

    public ReservationId ReservationId() {
        return reservationId;
    }

    public ReservedTime ReservedTime() {
        return reservedTime;
    }

    public ReservableRoom ReservableRoom() {
        return reservableRoom;
    }

    public User User() {
        return user;
    }
}
