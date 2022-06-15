package mrs.application.service.room;

public class ReservableRoomAlreadyReservedException extends RuntimeException {
    public ReservableRoomAlreadyReservedException(String message) {
        super(message);
    }
}
