package mrs.domain.model.reserve.room;

import java.io.Serializable;

/**
 * 予約可能会議室
 */
public class ReservableRoom implements Serializable {
    private ReservableRoomId reservableRoomId;
    private MeetingRoom meetingRoom;

    @Deprecated
    public ReservableRoom() {
    }

    public ReservableRoom(ReservableRoomId reservableRoomId) {
        this.reservableRoomId = reservableRoomId;
    }

    public ReservableRoomId getReservableRoomId() {
        return reservableRoomId;
    }

    public void setReservableRoomId(ReservableRoomId reservableRoomId) {
        this.reservableRoomId = reservableRoomId;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
