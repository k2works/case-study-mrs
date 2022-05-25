package mrs.infrastructure.datasource.user;

import mrs.IntegrationTest;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@IntegrationTest
public class UserDataSourceTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ReservableRoomMapper reservableRoomMapper;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    UserDataSource userDataSource;

    @BeforeEach
    public void setUp() {
        reservationMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void 会員を取得できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));

        Optional<User> result = userDataSource.findByUserId("1");
        assert (result.isPresent());
        assert (result.get().getUserId().equals("1"));
    }
}
