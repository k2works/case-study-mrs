package mrs.infrastructure.datasource.auth;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
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

@MybatisTest
@DisplayName("利用者エンティティ")
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
    public void 利用者が登録できる() {
        userMapper.insert(newUser("U999999"));

        UserId userId = new UserId("U999999");
        User user = userMapper.select(userId);
        assert (user.UserId().equals(userId));
        assert (user.Name().FirstName().equals("テスト"));
        assert (user.Name().LastName().equals("太郎"));
        assert (user.Password().Value().equals("a234567Z"));
        assert (user.RoleName().equals(RoleName.一般));
    }

    private User newUser(String userId) {
        return new User(userId, "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    public void 利用者を検索できる() {
        userMapper.insert(newUser("U999991"));
        userMapper.insert(new User("U999992", "テスト", "太郎", "a234567Z", RoleName.一般));

        List<User> users = userMapper.selectAll();
        assert (users.size() == 2);
    }

    @Test
    public void 複数の予約を保持している() {
        User user = newUser("U999991");
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
                reservation.ReservedTime().StartTime(),
                reservation.ReservedTime().EndTime(),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(addReservation);

        List<Reservation> reservations = reservationMapper.selectByKey(reservableRoomId.ReservedDate().Value(), reservableRoomId.RoomId().Value());
        User result = userMapper.select(user.UserId());
        assertEquals(reservations.size(), result.getReservations().size());
    }

    @Test
    public void 利用者を更新できる() {
        userMapper.insert(newUser("U999993"));
        UserId userId = new UserId("U999993");
        User user = userMapper.select(userId);
        User updateUser = new User(user.UserId().Value(), "更新1", "更新2", "A234567z", RoleName.管理者);
        userMapper.update(updateUser);

        User updatedUser = userMapper.select(userId);
        assert (updatedUser.Name().FirstName().equals("更新1"));
        assert (updatedUser.Name().LastName().equals("更新2"));
        assert (updatedUser.Password().Value().equals("A234567z"));
        assert (updatedUser.RoleName().equals(RoleName.管理者));
    }

    @Test
    public void 利用者を削除できる() {
        userMapper.insert(newUser("U999994"));

        UserId userId = new UserId("U999994");
        userMapper.delete(userId);
        User user = userMapper.select(userId);
        assert (user == null);
    }
}
