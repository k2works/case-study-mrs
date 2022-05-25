package mrs.infrastructure.datasource.reservation.room;

import mrs.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class RoomDataSourceTest {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomDataSource roomDataSource;

    @BeforeEach
    public void setUp() {
        roomMapper.deleteAll();
    }

}
