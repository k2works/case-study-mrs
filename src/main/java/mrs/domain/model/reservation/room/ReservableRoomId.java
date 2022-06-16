package mrs.domain.model.reservation.room;

import mrs.domain.model.facility.room.RoomId;
import mrs.domain.model.reservation.reservation.ReservedDate;

import java.time.LocalDate;

/**
 * 予約可能会議室ID
 */
public class ReservableRoomId {

    private RoomId roomId;

    private ReservedDate reservedDate;

    private String roomNumber;

    public ReservableRoomId(Integer roomId, LocalDate reservedDate) {
        this.roomId = new RoomId(roomId);
        this.reservedDate = new ReservedDate(reservedDate);
    }

    public ReservableRoomId() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reservedDate == null) ? 0 : reservedDate.hashCode());
        result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReservableRoomId other = (ReservableRoomId) obj;
        if (reservedDate == null) {
            if (other.reservedDate != null)
                return false;
        } else if (!reservedDate.equals(other.reservedDate)) {
            return false;
        }
        if (roomId == null) {
            if (other.roomId != null)
                return false;
        } else if (!roomId.equals(other.roomId))
            return false;
        return true;
    }

    public RoomId RoomId() {
        return roomId;
    }

    public ReservedDate ReservedDate() {
        return reservedDate;
    }

    public String RoomNumber() {
        return roomId.format();
    }
}
