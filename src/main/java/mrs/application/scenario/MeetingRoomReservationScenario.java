package mrs.application.scenario;

import mrs.application.service.reservation.ReservationService;
import mrs.application.service.room.RoomService;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会議室の予約シナリオ
 */
@Service
public class MeetingRoomReservationScenario {
    private final RoomService roomService;

    private final ReservationService reservationService;

    public MeetingRoomReservationScenario(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    /**
     * 予約可能な会議室を選択する
     */
    public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
        return reservationService.findReservations(reservableRoomId);
    }

    /**
     * 選択した会議室情報を取得する
     */
    public MeetingRoom findMeetingRoom(Integer roomId) {
        return roomService.findMeetingRoom(roomId);
    }

    /**
     * 会議室を予約する
     */
    public void reserve(Reservation reservation) {
        reservationService.reserve(reservation);
    }

    /**
     * 取り消したい会議室の予約を検索する
     */
    public Reservation findOne(Integer reservationId) {
        return reservationService.findOne(reservationId);
    }

    /**
     * 会議室の予約を取り消す
     */
    public void cancel(Reservation reservation) {
        reservationService.cancel(reservation);
    }

}
