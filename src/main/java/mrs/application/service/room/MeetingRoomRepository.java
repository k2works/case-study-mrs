package mrs.application.service.room;

import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.RoomId;

public interface MeetingRoomRepository {
    MeetingRoom getById(RoomId roomId);

    void save(MeetingRoom room);
}
