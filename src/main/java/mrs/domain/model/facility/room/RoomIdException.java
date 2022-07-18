package mrs.domain.model.facility.room;

/**
 * 会議室番号例外
 */
public class RoomIdException extends RuntimeException {
    public RoomIdException(String message) {
        super(message);
    }
}
