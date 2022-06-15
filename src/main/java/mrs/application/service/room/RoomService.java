package mrs.application.service.room;

import mrs.application.service.auth.UserAlreadyRegistException;
import mrs.domain.model.property.room.MeetingRoom;
import mrs.domain.model.property.room.RoomId;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.reservation.room.ReservableRoomList;
import mrs.infrastructure.datasource.Message;
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

    private final Message message;

    public RoomService(MeetingRoomRepository meetingRoomRepository, ReservableRoomRepository reservableRoomRepository, Message message) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservableRoomRepository = reservableRoomRepository;
        this.message = message;
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
        MeetingRoom result = meetingRoomRepository.getById(meetingRoom.RoomId());
        if (result != null) {
            throw new UserAlreadyRegistException(message.getMessageByKey("meeting_room_already_regist"));
        }

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
        MeetingRoom meetingRoom = meetingRoomRepository.getById(new RoomId(id));
        var usedReservableRoom = meetingRoom.getReservableRooms().stream().filter(reservableRoom -> reservableRoom.MeetingRoom().equals(meetingRoom));
        if (usedReservableRoom.findAny().isPresent()) {
            throw new MeetingRoomAlreadyUsedException(message.getMessageByKey("meeting_room_already_used"));
        }
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
        ReservableRoom result = reservableRoomRepository.findReservableRoom(reservableRoomId);
        if (result != null)
            throw new ReservableRoomAlreadyRegistException(message.getMessageByKey("reservable_room_already_regist"));

        reservableRoomRepository.save(reservableRoomId);
    }

    /**
     * 予約可能会議室を削除する
     */
    public void deleteReservableRoom(ReservableRoomId reservableRoomId) {
        ReservableRoom result = reservableRoomRepository.findReservableRoom(reservableRoomId);
        if (result == null)
            throw new ReservableNotExitException(message.getMessageByKey("reservable_room_not_exist"));
        if (result.getReservations().size() > 0)
            throw new ReservableRoomAlreadyReservedException(message.getMessageByKey("reservable_room_already_reserved"));

        reservableRoomRepository.deleteReservableRoom(reservableRoomId);
    }

    /**
     * 予約可能会議室を検索する
     */
    public ReservableRoom findReservableRoomById(ReservableRoomId reservableRoomId) {
        return reservableRoomRepository.findReservableRoom(reservableRoomId);
    }
}
