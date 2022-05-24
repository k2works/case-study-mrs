package mrs.domain.model.reserve.reservation;

import mrs.domain.model.reserve.room.ReservableRoom;
import mrs.domain.model.service_user.User;

import java.time.LocalTime;
import java.util.Objects;

/**
 * 予約
 */
public class Reservation {
    private Integer reservationId;

    private LocalTime startTime;

    private LocalTime endTime;

    private ReservableRoom reservableRoom;

    private User user;

    public boolean overlap(Reservation target) {
        if (!Objects.equals(reservableRoom.getReservableRoomId(), target.reservableRoom.getReservableRoomId())) {
            return false;
        }
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ReservableRoom getReservableRoom() {
        return reservableRoom;
    }

    public void setReservableRoom(ReservableRoom reservableRoom) {
        this.reservableRoom = reservableRoom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
