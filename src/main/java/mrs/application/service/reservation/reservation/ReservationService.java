package mrs.application.service.reservation.reservation;

import mrs.application.service.reservation.room.ReservableRoomRepository;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.reservation.ReservationList;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.Message;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 会議室の予約
 */
@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservableRoomRepository reservableRoomRepository;
    private final Message message;

    public ReservationService(ReservationRepository reservationRepository, ReservableRoomRepository reservableRoomRepository, Message message) {
        this.reservationRepository = reservationRepository;
        this.reservableRoomRepository = reservableRoomRepository;
        this.message = message;
    }

    /**
     * 会議室を予約する
     */
    public void reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.ReservableRoom().ReservableRoomId();
        // 悲観ロック
        Optional<ReservableRoom> reservable = Optional.ofNullable(reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId));
        if (reservable.isEmpty()) {
            throw new UnavailableReservationException(message.getMessageByKey("reservation_unable_reservation"));
        }
        // 重複チェック
        boolean overlap = reservationRepository.findByReservedDateAndRoomIdOrderByStartTimeAsc(reservableRoomId).stream().anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new AlreadyReservedException(message.getMessageByKey("reservation_already_reserved"));
        }
        // 予約情報の登録
        reservationRepository.save(reservation);
    }

    /**
     * 会議室の予約を取り消す
     */
    @PreAuthorize("hasRole('管理者') or #reservation.User.UserId == principal.user.UserId")
    public void cancel(@P("reservation") Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    /**
     * 会議室の予約を検索する
     */
    public Reservation findOne(ReservationId reservationId) {
        return reservationRepository.getById(reservationId);
    }

    /**
     * 会議室の予約一覧を取得する
     */
    public ReservationList findReservations(ReservableRoomId reservableRoomId) {
        List<Reservation> result = reservationRepository.findByReservedDateAndRoomIdOrderByStartTimeAsc(reservableRoomId);
        return new ReservationList(result);
    }
}
