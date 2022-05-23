package mrs.infrastructure.datasource.reserve.room;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReservableRoomDataSourceTest {
    @Autowired
    private ReservableRoomMapper reservableRoomMapper;

    @Autowired
    private ReservableRoomDataSource reservableRoomDataSource;

    @BeforeEach
    public void setUp() {
        reservableRoomMapper.deleteAll();
    }
}
