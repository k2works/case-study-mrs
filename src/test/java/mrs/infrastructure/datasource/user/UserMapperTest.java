package mrs.infrastructure.datasource.user;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.domain.model.user.UserId;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class UserMapperTest {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservableRoomMapper reservableRoomMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void ユーザーが登録できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));

        UserId userId = new UserId("1");
        User user = userMapper.select(userId);
        assert (user.UserId().equals(userId));
        assert (user.FirstName().equals("テスト"));
        assert (user.LastName().equals("太郎"));
        assert (user.Password().equals("password"));
        assert (user.RoleName().equals(RoleName.USER));
    }

    @Test
    public void ユーザーを検索できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));
        userMapper.insert(new User("2", "テスト", "太郎", "password", RoleName.USER));

        List<User> users = userMapper.selectAllJoin();
        assert (users.size() == 2);
    }

    @Test
    public void 複数の予約を保持している() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

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
                reservation.StartTime(),
                reservation.EndTime(),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(addReservation);

        List<Reservation> reservations = reservationMapper.selectByKey(reservableRoomId.ReservedDate(), reservableRoomId.RoomId());
        User result = userMapper.select(user.UserId());
        assertEquals(reservations.size(), result.getReservations().size());
    }

    @Test
    public void ユーザーを更新できる() {
        userMapper.insert(new User("3", "テスト", "太郎", "password", RoleName.USER));
        UserId userId = new UserId("3");
        User user = userMapper.select(userId);
        User updateUser = new User(user.UserId().Value(), "更新1", "更新2", "updated", RoleName.ADMIN);
        userMapper.update(updateUser);

        User updatedUser = userMapper.select(userId);
        assert (updatedUser.FirstName().equals("更新1"));
        assert (updatedUser.LastName().equals("更新2"));
        assert (updatedUser.Password().equals("updated"));
        assert (updatedUser.RoleName().equals(RoleName.ADMIN));
    }

    @Test
    public void ユーザーを削除できる() {
        userMapper.insert(new User("4", "テスト", "太郎", "password", RoleName.USER));

        UserId userId = new UserId("4");
        userMapper.delete(userId);
        User user = userMapper.select(userId);
        assert (user == null);
    }
}
