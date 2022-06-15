package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.property.room.MeetingRoom;
import mrs.domain.model.property.room.RoomId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    void insert(MeetingRoom meetingRoom);

    MeetingRoom select(RoomId roomId);

    List<MeetingRoom> selectAll();

    void update(MeetingRoom meetingRoom);

    void delete(RoomId roomId);

    void deleteAll();
}
