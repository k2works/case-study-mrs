package mrs.infrastructure.datasource.reservation.room;

import mrs.application.service.reservation.room.ReservableRoomRepository;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservableRoomDataSource implements ReservableRoomRepository {
    ReservableRoomMapper reservableRoomMapper;

    public ReservableRoomDataSource(ReservableRoomMapper reservableRoomMapper) {
        this.reservableRoomMapper = reservableRoomMapper;
    }

    @Override
    public List<ReservableRoom> findByReservedDateOrderByRoomIdAsc(ReservedDate reservedDate) {
        return reservableRoomMapper.selectByReservedDateOrderByRoomIdAsc(reservedDate);
    }

    @Override
    public ReservableRoom findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId) {
        return reservableRoomMapper.select(reservableRoomId);
    }

    @Override
    public void save(ReservableRoomId id) {
        reservableRoomMapper.insert(id);
    }

    @Override
    public List<ReservableRoom> findAll() {
        return reservableRoomMapper.selectAll();
    }

    @Override
    public ReservableRoom findReservableRoom(ReservableRoomId reservableRoomId) {
        return reservableRoomMapper.select(reservableRoomId);
    }

    @Override
    public void deleteReservableRoom(ReservableRoomId reservableRoomId) {
        reservableRoomMapper.delete(reservableRoomId);
    }
}
