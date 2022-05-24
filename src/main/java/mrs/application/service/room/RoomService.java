package mrs.application.service.room;

import mrs.domain.model.reserve.room.MeetingRoom;
import mrs.domain.model.room.ReservableRoom;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * 会議室の検索
 */
@Service
@Transactional
public class RoomService {
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservableRoomRepository reservableRoomRepository;

    public RoomService(MeetingRoomRepository meetingRoomRepository, ReservableRoomRepository reservableRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservableRoomRepository = reservableRoomRepository;
    }

    /**
     * 会議室を検索する
     */
    public MeetingRoom findMeetingRoom(Integer roomId) {
        return meetingRoomRepository.getById(roomId);
    }

    /**
     * 予約可能な会議室を検索する
     */
    public List<ReservableRoom> findReservableRooms(LocalDate date) {
        return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
    }
}
