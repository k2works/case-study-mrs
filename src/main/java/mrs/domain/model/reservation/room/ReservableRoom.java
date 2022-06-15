package mrs.domain.model.reservation.room;

import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 予約可能会議室
 */
public class ReservableRoom {
    private ReservableRoomId reservableRoomId;
    private MeetingRoom meetingRoom;
    private List<Reservation> reservations;

    @Deprecated
    public ReservableRoom() {
    }

    public ReservableRoom(ReservableRoomId reservableRoomId) {
        this.reservableRoomId = reservableRoomId;
    }

    public ReservableRoomId ReservableRoomId() {
        return reservableRoomId;
    }

    public MeetingRoom MeetingRoom() {
        return meetingRoom;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
