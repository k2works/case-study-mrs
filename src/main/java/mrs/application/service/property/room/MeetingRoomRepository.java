package mrs.application.service.property.room;

import mrs.domain.model.property.room.MeetingRoom;
import mrs.domain.model.property.room.RoomId;

import java.util.List;

public interface MeetingRoomRepository {
    MeetingRoom getById(RoomId roomId);

    void save(MeetingRoom room);

    List<MeetingRoom> findAll();

    void deleteById(RoomId roomId);
}
