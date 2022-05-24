package mrs.application.service.room;

import mrs.domain.model.reserve.room.MeetingRoom;

public interface MeetingRoomRepository {
    MeetingRoom getById(Integer roomId);
}
