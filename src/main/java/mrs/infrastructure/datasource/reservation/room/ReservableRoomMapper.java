package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservableRoomMapper {
    void insert(ReservableRoomId reservableRoomId);

    ReservableRoom select(ReservableRoomId reservableRoomId);

    List<ReservableRoom> selectAll();

    void update(ReservableRoomId reservableRoomId);

    void delete(ReservableRoomId reservableRoomId);

    void deleteAll();

    List<ReservableRoom> selectByReservedDateOrderByRoomIdAsc(ReservedDate reservedDate);
}
