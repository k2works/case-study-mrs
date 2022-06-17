package mrs.infrastructure.datasource.reservation.reservation;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.auth.UserMapper;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@MybatisTest
@DisplayName("予約エンティティ")
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
                reservation.ReservedTime().StartTime(),
                reservation.ReservedTime().EndTime(),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(addReservation);
    }

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    public void 予約を登録できる() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertOne(reservableRoomId, user);
        ReservationId reservationId = new ReservationId(1);
        Reservation reservation = reservationMapper.select(reservationId);

        Reservation result = reservationMapper.select(reservation.ReservationId());
        assertEquals(reservationId, result.ReservationId());
        assertEquals(LocalTime.of(10, 0), result.ReservedTime().EndTime());
        assertEquals(LocalTime.of(9, 0), result.ReservedTime().StartTime());
        assertEquals(reservableRoomId.ReservedDate(), result.ReservableRoom().ReservableRoomId().ReservedDate());
        assertEquals(reservableRoomId, result.ReservableRoom().ReservableRoomId());
        assertEquals(meetingRoom.RoomName(), result.ReservableRoom().MeetingRoom().RoomName());
        assertEquals(user.UserId(), result.User().UserId());
        assertEquals(user.Name().LastName(), result.User().Name().LastName());
        assertEquals(user.Name().FirstName(), result.User().Name().FirstName());
        assertEquals(user.RoleName(), result.User().RoleName());
    }

    @Test
    public void 予約を検索できる() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertTwo(reservableRoomId, user);

        List<Reservation> result = reservationMapper.selectAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).ReservationId().Value());
    }

    @Test
    public void 予約を複合キーで検索できる() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertTwo(reservableRoomId, user);

        List<Reservation> result = reservationMapper.selectByKey(reservableRoomId.ReservedDate().Value(), reservableRoomId.RoomId().Value());
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).ReservationId().Value());
    }

    @Test
    public void 予約を更新できる() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertOne(reservableRoomId, user);
        ReservationId reservationId = new ReservationId(1);
        Reservation reservation = reservationMapper.select(reservationId);
        reservationMapper.update(reservation);
        Reservation updateReservation = new Reservation(
                reservation.ReservationId().Value(),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.update(updateReservation);

        Reservation result = reservationMapper.select(reservation.ReservationId());
        assertEquals(1, result.ReservationId().Value());
        assertEquals(LocalTime.of(11, 0), result.ReservedTime().EndTime());
        assertEquals(LocalTime.of(10, 0), result.ReservedTime().StartTime());
    }

    @Test
    public void 予約を削除できる() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        insertOne(reservableRoomId, user);
        ReservationId reservationId = new ReservationId(1);
        Reservation reservation = reservationMapper.select(reservationId);

        reservationMapper.delete(reservation.ReservationId());

        Reservation result = reservationMapper.select(reservation.ReservationId());
        assertNull(result);
    }
}
