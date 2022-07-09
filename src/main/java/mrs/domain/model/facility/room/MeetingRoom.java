package mrs.domain.model.facility.room;

import mrs.domain.model.reservation.room.ReservableRoom;

import java.util.List;

/**
 * 会議室
 */
public class MeetingRoom {
    private RoomId roomId;

    private String roomName;

    private String roomNumber;

    private List<ReservableRoom> reservableRooms;

    @Deprecated
    public MeetingRoom() {
    }

    public MeetingRoom(Integer roomId, String roomName) {
        if (roomId == null) throw new IllegalArgumentException("会議室番号が未入力です");
        if (roomName == null || roomName.isEmpty() || roomName.isBlank())
            throw new IllegalArgumentException("会議室名が未入力です");

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

    public String RoomNumber() {
        return roomId.format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingRoom that)) return false;

        return roomId.equals(that.roomId);
    }

    @Override
    public int hashCode() {
        return roomId.hashCode();
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                '}';
    }

}
