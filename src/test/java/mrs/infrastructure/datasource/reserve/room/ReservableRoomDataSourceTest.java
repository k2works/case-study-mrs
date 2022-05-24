package mrs.infrastructure.datasource.reserve.room;

import mrs.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
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
