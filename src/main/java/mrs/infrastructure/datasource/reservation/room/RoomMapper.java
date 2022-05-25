package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.reservation.room.MeetingRoom;
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
