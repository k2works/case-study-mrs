package mrs;

import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;
import mrs.infrastructure.datasource.user.UserMapper;

public interface TestDataFactory {
    void setUp();

    UserMapper getUserMapper();

    RoomMapper getRoomMapper();

    ReservableRoomMapper getReservableRoomMapper();

    ReservationMapper getReservationMapper();

    ReservableRoomId getReservableRoomId();

}
