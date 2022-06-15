package mrs.application.service.room;

public class ReservableRoomAlreadyUsedException extends RuntimeException {
    public ReservableRoomAlreadyUsedException(String message) {
        super(message);
    }
}
