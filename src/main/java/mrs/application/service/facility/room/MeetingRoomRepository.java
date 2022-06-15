package mrs.application.service.facility.room;

import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.facility.room.RoomId;

import java.util.List;

public interface MeetingRoomRepository {
    MeetingRoom getById(RoomId roomId);

    void save(MeetingRoom room);

    List<MeetingRoom> findAll();

    void deleteById(RoomId roomId);
}
