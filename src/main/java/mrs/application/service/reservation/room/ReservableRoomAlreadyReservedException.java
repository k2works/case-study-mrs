package mrs.application.service.reservation.room;

public class ReservableRoomAlreadyReservedException extends RuntimeException {
    public ReservableRoomAlreadyReservedException(String message) {
        super(message);
    }
}
