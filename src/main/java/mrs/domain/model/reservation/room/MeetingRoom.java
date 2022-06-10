package mrs.domain.model.reservation.room;

import mrs.domain.model.reservation.reservation.ReservableRoom;

import java.util.List;

/**
 * 会議室
 */
public class MeetingRoom {
    private RoomId roomId;

    private String roomName;

    private List<ReservableRoom> reservableRooms;

    @Deprecated
    public MeetingRoom() {
    }

    public MeetingRoom(Integer roomId, String roomName) {
        this.roomId = new RoomId(roomId);
        this.roomName = roomName;
    }

    public RoomId RoomId() {
        return roomId;
    }

    public String RoomName() {
        return roomName;
    }

    public List<ReservableRoom> getReservableRooms() {
        return reservableRooms;
    }
}
