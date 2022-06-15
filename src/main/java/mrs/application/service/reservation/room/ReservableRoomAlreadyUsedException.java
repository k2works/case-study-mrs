package mrs.application.service.reservation.room;

public class ReservableRoomAlreadyUsedException extends RuntimeException {
    public ReservableRoomAlreadyUsedException(String message) {
        super(message);
    }
}
