package mrs.application.service.room;

public class ReservableNotExitException extends RuntimeException {
    public ReservableNotExitException(String message) {
        super(message);
    }
}
