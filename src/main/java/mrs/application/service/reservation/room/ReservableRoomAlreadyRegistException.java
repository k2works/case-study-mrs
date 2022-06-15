package mrs.application.service.reservation.room;

public class ReservableRoomAlreadyRegistException extends RuntimeException {
    public ReservableRoomAlreadyRegistException(String message) {
        super(message);
    }
}
