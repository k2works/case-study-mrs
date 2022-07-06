package mrs.presentation.api.meetingRoom;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "会議室")
public class MeetingRoomResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roomId;
    private String roomName;

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
