package mrs.application.service.room;

import mrs.domain.model.reservation.reservation.ReservableRoom;
import mrs.domain.model.reservation.reservation.ReservableRoomId;
import mrs.domain.model.reservation.reservation.ReservableRoomList;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.RoomId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public MeetingRoom findMeetingRoom(RoomId roomId) {
        return meetingRoomRepository.getById(roomId);
    }

    /**
     * 予約可能な会議室を検索する
     */
    public Optional<ReservableRoomList> findReservableRooms(ReservedDate date) {
        List<ReservableRoom> result = reservableRoomRepository.findByReservedDateOrderByRoomIdAsc(date);
        return Optional.of(new ReservableRoomList(result));
    }

    /**
     * 会議室一覧を取得する
     */
    public List<MeetingRoom> findAll() {
        return meetingRoomRepository.findAll();
    }

    /**
     * 会議室を登録する
     */
    public void registMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomRepository.save(meetingRoom);
    }

    /**
     * 会議室を更新する
     */
    public void updateMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomRepository.save(meetingRoom);
    }

    /**
     * 会議室を削除する
     */
    public void deleteMeetingRoom(int id) {
        meetingRoomRepository.deleteById(new RoomId(id));
    }

    /**
     * 予約可能会議室一覧を取得する
     */
    public List<ReservableRoom> findAllReservableRooms() {
        return reservableRoomRepository.findAll();
    }

    /**
     * 予約可能会議室を登録する
     */
    public void registReservableRoom(ReservableRoomId reservableRoomId) {
        reservableRoomRepository.save(reservableRoomId);
    }

    /**
     * 予約可能会議室を更新する
     */
    public void updateReservableRoom(ReservableRoomId reservableRoomId) {
    }

    /**
     * 予約可能会議室を削除する
     */
    public void deleteReservableRoom(ReservableRoomId reservableRoomId) {
    }
}
