package mrs.application.service.room;

import mrs.domain.model.reserve.room.ReservableRoom;
import mrs.domain.model.reserve.room.ReservableRoomId;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;

/**
 * 会議室レポジトリ
 */
public interface ReservableRoomRepository {
    List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(LocalDate reservedDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    ReservableRoom findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId);

    void save(ReservableRoomId id);
}
