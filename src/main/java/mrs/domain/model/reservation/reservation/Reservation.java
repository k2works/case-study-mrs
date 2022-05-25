package mrs.domain.model.reservation.reservation;

import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.User;

import java.time.LocalTime;
import java.util.Objects;

/**
 * 予約
 */
public class Reservation {
    private ReservationId reservationId;

    private LocalTime startTime;

    private LocalTime endTime;

    private ReservableRoom reservableRoom;

    private User user;

    @Deprecated
    public Reservation() {
    }

    ;

    public Reservation(Integer reservationId, LocalTime startTime, LocalTime endTime, ReservableRoomId reservableRoomId, User user) {
        this.reservationId = new ReservationId(reservationId);
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservableRoom = new ReservableRoom(reservableRoomId);
        this.user = user;
    }

    public boolean overlap(Reservation target) {
        if (!Objects.equals(reservableRoom.ReservableRoomId(), target.reservableRoom.ReservableRoomId())) {
            return false;
        }
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }

    public ReservationId ReservationId() {
        return reservationId;
    }

    public LocalTime StartTime() {
        return startTime;
    }

    public LocalTime EndTime() {
        return endTime;
    }

    public ReservableRoom ReservableRoom() {
        return reservableRoom;
    }

    public User User() {
        return user;
    }
}
