package mrs.presentation.meetingRoom;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MeetingRoomForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer roomId;

    @NotNull
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
