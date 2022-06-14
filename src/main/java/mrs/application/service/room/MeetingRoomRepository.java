package mrs.application.service.room;

import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.RoomId;

import java.util.List;

public interface MeetingRoomRepository {
    MeetingRoom getById(RoomId roomId);

    void save(MeetingRoom room);

    List<MeetingRoom> findAll();

    void deleteById(RoomId roomId);
}
