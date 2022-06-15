package mrs.application.service.reservation.room;

import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
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

    List<ReservableRoom> findAll();

    ReservableRoom findReservableRoom(ReservableRoomId reservableRoomId);

    void deleteReservableRoom(ReservableRoomId reservableRoomId);
}
