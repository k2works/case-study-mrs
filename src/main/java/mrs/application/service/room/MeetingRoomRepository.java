package mrs.application.service.room;

import mrs.domain.model.reservation.room.MeetingRoom;

public interface MeetingRoomRepository {
    MeetingRoom getById(Integer roomId);

    void save(MeetingRoom room);
}
