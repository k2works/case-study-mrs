package mrs.infrastructure.datasource.reservation.room;

import mrs.application.service.room.ReservableRoomRepository;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservableRoomDataSource implements ReservableRoomRepository {
    ReservableRoomMapper reservableRoomMapper;

    public ReservableRoomDataSource(ReservableRoomMapper reservableRoomMapper) {
        this.reservableRoomMapper = reservableRoomMapper;
    }

    @Override
    public List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(LocalDate reservedDate) {
        return reservableRoomMapper.findByReservableRoomId_ReservedDateOrderByReservableRoomId_RoomIdAsc(reservedDate);
    }

    @Override
    public ReservableRoom findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId) {
        return reservableRoomMapper.select(reservableRoomId);
    }

    @Override
    public void save(ReservableRoomId id) {
        reservableRoomMapper.insert(id);
    }

}
