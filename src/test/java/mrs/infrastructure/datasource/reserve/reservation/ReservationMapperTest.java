package mrs.infrastructure.datasource.reserve.reservation;

import mrs.domain.model.reserve.reservation.Reservation;
import mrs.domain.model.reserve.room.MeetingRoom;
import mrs.domain.model.reserve.room.ReservableRoomId;
import mrs.domain.model.service_user.RoleName;
import mrs.domain.model.service_user.User;
import mrs.infrastructure.datasource.reserve.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reserve.room.RoomMapper;
import mrs.infrastructure.datasource.service_user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@MybatisTest
public class ReservationMapperTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ReservableRoomMapper reservableRoomMapper;
    @Autowired
    ReservationMapper reservationMapper;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
        roomMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        reservationMapper.deleteAll();
    }

    @Test
    public void 予約を登録できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);

        Reservation result = reservationMapper.select(reservation.getReservationId());
        assertEquals(1, result.getReservationId());
        assertEquals(LocalTime.of(10, 0), result.getEndTime());
        assertEquals(LocalTime.of(9, 0), result.getStartTime());
        assertEquals(reservableRoomId.getReservedDate(), result.getReservableRoom().getReservableRoomId().getReservedDate());
        assertEquals(reservableRoomId, result.getReservableRoom().getReservableRoomId());
        assertEquals(user.getUserId(), result.getUser().getUserId());
    }

    @Test
    public void 予約を検索できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);
        reservation.setReservationId(2);
        reservationMapper.insert(reservation);

        List<Reservation> result = reservationMapper.selectAllJoin();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getReservationId());
    }

    @Test
    public void 予約を更新できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);

        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(11, 0));
        reservation.setStartTime(LocalTime.of(10, 0));
        reservationMapper.update(reservation);

        Reservation result = reservationMapper.select(reservation.getReservationId());
        assertEquals(1, result.getReservationId());
        assertEquals(LocalTime.of(11, 0), result.getEndTime());
        assertEquals(LocalTime.of(10, 0), result.getStartTime());
    }

    @Test
    public void 予約を削除できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);

        reservationMapper.delete(reservation.getReservationId());

        Reservation result = reservationMapper.select(reservation.getReservationId());
        assertNull(result);
    }
}
