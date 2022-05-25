package mrs;

import mrs.domain.model.reserve.room.ReservableRoomId;
import mrs.infrastructure.datasource.reserve.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reserve.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reserve.room.RoomMapper;
import mrs.infrastructure.datasource.service_user.UserMapper;

public interface TestDataFactory {
    void setUp();

    UserMapper getUserMapper();

    RoomMapper getRoomMapper();

    ReservableRoomMapper getReservableRoomMapper();

    ReservationMapper getReservationMapper();

    ReservableRoomId getReservableRoomId();

}
