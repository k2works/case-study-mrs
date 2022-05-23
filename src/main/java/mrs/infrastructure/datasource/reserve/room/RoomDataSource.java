package mrs.infrastructure.datasource.reserve.room;

import org.springframework.stereotype.Repository;

@Repository
public class RoomDataSource {
    RoomMapper roomMapper;

    public RoomDataSource(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }
}
