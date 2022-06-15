package mrs.application.service.reservation.reservation;

public class UnavailableReservationException extends RuntimeException {
    public UnavailableReservationException(String message) {
        super(message);
    }
}
