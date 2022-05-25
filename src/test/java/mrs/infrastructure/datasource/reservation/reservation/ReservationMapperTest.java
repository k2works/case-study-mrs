package mrs.infrastructure.datasource.reservation.reservation;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;
import mrs.infrastructure.datasource.user.UserMapper;
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
        reservationMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        userMapper.deleteAll();
    }

    private void insertOne(ReservableRoomId reservableRoomId, User user) {
        Reservation reservation = new Reservation(
                1,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(reservation);
    }

    private void insertTwo(ReservableRoomId reservableRoomId, User user) {
        Reservation reservation = new Reservation(
                1,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(reservation);
        Reservation addReservation = new Reservation(
                2,
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(addReservation);
    }

    @Test
    public void 予約を登録できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertOne(reservableRoomId, user);
        Reservation reservation = reservationMapper.select(1);

        Reservation result = reservationMapper.select(reservation.getReservationId());
        assertEquals(1, result.getReservationId());
        assertEquals(LocalTime.of(10, 0), result.getEndTime());
        assertEquals(LocalTime.of(9, 0), result.getStartTime());
        assertEquals(reservableRoomId.ReservedDate(), result.getReservableRoom().ReservableRoomId().ReservedDate());
        assertEquals(reservableRoomId, result.getReservableRoom().ReservableRoomId());
        assertEquals(meetingRoom.RoomName(), result.getReservableRoom().MeetingRoom().RoomName());
        assertEquals(user.UserId(), result.getUser().UserId());
        assertEquals(user.LastName(), result.getUser().LastName());
        assertEquals(user.FirstName(), result.getUser().FirstName());
        assertEquals(user.RoleName(), result.getUser().RoleName());
    }

    @Test
    public void 予約を検索できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertTwo(reservableRoomId, user);

        List<Reservation> result = reservationMapper.selectAllJoin();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getReservationId());
    }

    @Test
    public void 予約を複合キーで検索できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertTwo(reservableRoomId, user);

        List<Reservation> result = reservationMapper.selectByKey(reservableRoomId.ReservedDate(), reservableRoomId.RoomId());
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

        insertOne(reservableRoomId, user);
        Reservation reservation = reservationMapper.select(1);
        reservationMapper.update(reservation);
        Reservation updateReservation = new Reservation(
                reservation.getReservationId(),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.update(updateReservation);

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

        insertOne(reservableRoomId, user);
        Reservation reservation = reservationMapper.select(1);

        reservationMapper.delete(reservation.getReservationId());

        Reservation result = reservationMapper.select(reservation.getReservationId());
        assertNull(result);
    }
}
