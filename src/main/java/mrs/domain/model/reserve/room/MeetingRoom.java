package mrs.domain.model.reserve.room;

/**
 * 会議室
 */
public class MeetingRoom {
    private Integer roomId;

    private String roomName;

    @Deprecated
    public MeetingRoom() {
    }

    public MeetingRoom(Integer roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
