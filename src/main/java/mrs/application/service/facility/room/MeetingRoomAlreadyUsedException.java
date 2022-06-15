package mrs.application.service.facility.room;

public class MeetingRoomAlreadyUsedException extends RuntimeException {
    public MeetingRoomAlreadyUsedException(String message) {
        super(message);
    }
}
