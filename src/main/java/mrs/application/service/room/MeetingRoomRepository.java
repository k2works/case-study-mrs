package mrs.application.service.room;

import mrs.domain.model.room.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
}
