package mrs.infrastructure.datasource.reserve.room;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
