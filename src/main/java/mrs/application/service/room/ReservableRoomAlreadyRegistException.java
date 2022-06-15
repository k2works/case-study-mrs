package mrs.application.service.room;

public class ReservableRoomAlreadyRegistException extends RuntimeException {
    public ReservableRoomAlreadyRegistException(String message) {
        super(message);
    }
}
