package mrs.presentation.reservableRoom;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class ReservableRoomForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer roomId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reservedDate;

    private Map<Integer, String> meetingRooms;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Map<Integer, String> getMeetingRooms() {
        return meetingRooms;
    }

    public void setMeetingRooms(Map<Integer, String> meetingRooms) {
        this.meetingRooms = meetingRooms;
    }
}
