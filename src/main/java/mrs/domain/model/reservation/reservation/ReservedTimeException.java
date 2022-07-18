package mrs.domain.model.reservation.reservation;

/**
 * 予約時間例外
 */
public class ReservedTimeException extends RuntimeException {
    public ReservedTimeException(String message) {
        super(message);
    }
}
