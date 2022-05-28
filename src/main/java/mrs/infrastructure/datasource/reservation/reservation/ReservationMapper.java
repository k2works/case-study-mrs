package mrs.infrastructure.datasource.reservation.reservation;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.UserId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
    void insert(Reservation reservation);

    Reservation select(ReservationId reservationId);

    List<Reservation> selectAll();

    List<Reservation> selectByKey(@Param("reservedDate") LocalDate reservedDate, @Param("roomId") Integer roomId);

    List<Reservation> selectByUserId(UserId userId);

    void update(Reservation reservation);

    void delete(ReservationId reservationId);

    void deleteAll();

    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);
}
