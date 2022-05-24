package mrs.infrastructure.datasource.reserve.room;

import mrs.application.service.room.MeetingRoomRepository;
import mrs.domain.model.reserve.room.MeetingRoom;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDataSource implements MeetingRoomRepository {
    RoomMapper roomMapper;

    public RoomDataSource(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public MeetingRoom getById(Integer roomId) {
        return roomMapper.select(roomId);
    }

    @Override
    public void save(MeetingRoom room) {

    }
}
