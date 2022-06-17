package mrs.application.service.reservation.room;

import mrs.application.service.facility.room.MeetingRoomRepository;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.reservation.room.ReservableRoomList;
import mrs.infrastructure.datasource.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 会議室の検索
 */
@Service
@Transactional
public class ReservableRoomService {
    private final ReservableRoomRepository reservableRoomRepository;

    private final Message message;

    public ReservableRoomService(ReservableRoomRepository reservableRoomRepository, MeetingRoomRepository meetingRoomRepositor, Message message) {
        this.reservableRoomRepository = reservableRoomRepository;
        this.message = message;
    }

    /**
     * 予約可能な会議室を検索する
     */
    public Optional<ReservableRoomList> findReservableRooms(ReservedDate date) {
        List<ReservableRoom> result = reservableRoomRepository.findByReservedDateOrderByRoomIdAsc(date);
        return Optional.of(new ReservableRoomList(result));
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
