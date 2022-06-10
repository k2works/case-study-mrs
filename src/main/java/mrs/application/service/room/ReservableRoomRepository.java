package mrs.application.service.room;

import mrs.domain.model.reservation.reservation.ReservableRoom;
import mrs.domain.model.reservation.reservation.ReservableRoomId;
import mrs.domain.model.reservation.reservation.ReservedDate;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * 会議室レポジトリ
 */
public interface ReservableRoomRepository {
    List<ReservableRoom> findByReservedDateOrderByRoomIdAsc(ReservedDate reservedDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    ReservableRoom findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId);

    void save(ReservableRoomId id);
}
