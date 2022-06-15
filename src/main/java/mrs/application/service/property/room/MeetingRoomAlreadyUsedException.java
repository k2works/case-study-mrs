package mrs.application.service.property.room;

public class MeetingRoomAlreadyUsedException extends RuntimeException {
    public MeetingRoomAlreadyUsedException(String message) {
        super(message);
    }
}
