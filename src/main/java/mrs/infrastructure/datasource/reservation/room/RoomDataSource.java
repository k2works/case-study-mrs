package mrs.infrastructure.datasource.reservation.room;

import mrs.application.service.room.MeetingRoomRepository;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.RoomId;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDataSource implements MeetingRoomRepository {
    RoomMapper roomMapper;

    public RoomDataSource(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public MeetingRoom getById(RoomId roomId) {
        return roomMapper.select(roomId);
    }

    @Override
    public void save(MeetingRoom room) {

    }
}
