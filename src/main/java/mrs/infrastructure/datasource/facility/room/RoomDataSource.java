package mrs.infrastructure.datasource.facility.room;

import mrs.application.service.facility.room.MeetingRoomRepository;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.facility.room.RoomId;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        MeetingRoom result = roomMapper.select(room.RoomId());
        if (result == null) {
            roomMapper.insert(room);
        } else {
            roomMapper.update(room);
        }
    }

    @Override
    public List<MeetingRoom> findAll() {
        return roomMapper.selectAll();
    }

    @Override
    public void deleteById(RoomId roomId) {
        roomMapper.delete(roomId);
    }
}
