package mrs.application.service.room;

public class MeetingRoomAlreadyUsedException extends RuntimeException {
    public MeetingRoomAlreadyUsedException(String message) {
        super(message);
    }
}
