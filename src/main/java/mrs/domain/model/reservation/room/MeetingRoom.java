package mrs.domain.model.reservation.room;

import java.util.List;

/**
 * 会議室
 */
public class MeetingRoom {
    private Integer roomId;

    private String roomName;

    private List<ReservableRoom> reservableRooms;

    @Deprecated
    public MeetingRoom() {
    }

    public MeetingRoom(Integer roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public Integer RoomId() {
        return roomId;
    }

    public String RoomName() {
        return roomName;
    }

    public List<ReservableRoom> getReservableRooms() {
        return reservableRooms;
    }
}
