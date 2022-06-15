package mrs.application.service.reservation.room;

public class ReservableNotExitException extends RuntimeException {
    public ReservableNotExitException(String message) {
        super(message);
    }
}
