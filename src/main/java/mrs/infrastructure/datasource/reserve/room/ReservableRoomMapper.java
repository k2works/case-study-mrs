package mrs.infrastructure.datasource.reserve.room;

import mrs.domain.model.reserve.room.ReservableRoom;
import mrs.domain.model.reserve.room.ReservableRoomId;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservableRoomMapper {
    void insert(ReservableRoomId reservableRoomId);

    ReservableRoom select(ReservableRoomId reservableRoomId);

    List<ReservableRoom> selectAllJoin();

    void update(ReservableRoomId reservableRoomId);

    void delete(ReservableRoomId reservableRoomId);

    void deleteAll();

    List<ReservableRoom> findByReservableRoomId_ReservedDateOrderByReservableRoomId_RoomIdAsc(LocalDate reservedDate);
}
