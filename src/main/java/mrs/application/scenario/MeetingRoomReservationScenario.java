package mrs.application.scenario;

import mrs.application.service.facility.room.MeetingRoomService;
import mrs.application.service.reservation.reservation.ReservationService;
import mrs.application.service.reservation.room.ReservableRoomService;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.facility.room.RoomId;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.reservation.ReservationList;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.reservation.room.ReservableRoomList;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * 会議室の予約シナリオ
 */
@Service
public class MeetingRoomReservationScenario {

    private final MeetingRoomService meetingRoomService;

    private final ReservableRoomService reservableRoomService;

    private final ReservationService reservationService;

    public MeetingRoomReservationScenario(MeetingRoomService meetingRoomService, ReservableRoomService reservableRoomService, ReservationService reservationService) {
        this.meetingRoomService = meetingRoomService;
        this.reservableRoomService = reservableRoomService;
        this.reservationService = reservationService;
    }

    /**
     * 会議室を登録する
     */
    public void registMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomService.registMeetingRoom(meetingRoom);
    }

    /**
     * 予約可能会議室を登録する
     */
    public void registReservableRoom(ReservableRoomId reservableRoomId) {
        reservableRoomService.registReservableRoom(reservableRoomId);
    }

    /**
     * 予約可能な会議室を検索する
     */
    public Optional<ReservableRoomList> findReservableRooms(ReservedDate date) {
        return reservableRoomService.findReservableRooms(date);
    }

    /**
     * 予約可能な会議室を選択する
     */
    public Optional<ReservationList> findReservations(ReservableRoomId reservableRoomId) {
        return Optional.of(reservationService.findReservations(reservableRoomId));
    }

    /**
     * 選択した会議室情報を取得する
     */
    public MeetingRoom findMeetingRoom(RoomId roomId) {
        return meetingRoomService.findMeetingRoom(roomId);
    }

    /**
     * 会議室を予約する
     */
    public void reserve(Reservation reservation) {
        reservationService.reserve(reservation);
    }

    /**
     * 取り消したい会議室の予約を選択する
     */
    public Reservation findOne(ReservationId reservationId) {
        return reservationService.findOne(reservationId);
    }

    /**
     * 会議室の予約を取り消す
     */
    public void cancel(Reservation reservation) {
        reservationService.cancel(reservation);
    }

    /**
     * 会議室リストボックスを作成する
     */
    public Map<Integer, String> createRoomNameMap() {
        return meetingRoomService.createRoomNameMap();
    }
}
