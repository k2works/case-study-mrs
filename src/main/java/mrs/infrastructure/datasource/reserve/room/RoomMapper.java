package mrs.infrastructure.datasource.reserve.room;

import mrs.domain.model.reserve.room.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    void insert(MeetingRoom meetingRoom);

    MeetingRoom select(Integer roomId);

    List<MeetingRoom> selectAllJoin();

    void update(MeetingRoom meetingRoom);

    void delete(Integer roomId);

    void deleteAll();
}
