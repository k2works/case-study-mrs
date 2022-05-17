package mrs.application.service.room;

import mrs.domain.model.room.MeetingRoom;
import mrs.domain.model.room.ReservableRoom;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RoomService {
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservableRoomRepository reservableRoomRepository;

    public RoomService(MeetingRoomRepository meetingRoomRepository, ReservableRoomRepository reservableRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservableRoomRepository = reservableRoomRepository;
    }

    public MeetingRoom findMeetingRoom(Integer roomId) {
        return meetingRoomRepository.getById(roomId);
    }

    public List<ReservableRoom> findReservableRooms(LocalDate date) {
        return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
    }
}
