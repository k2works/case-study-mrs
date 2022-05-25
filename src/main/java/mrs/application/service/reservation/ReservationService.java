package mrs.application.service.reservation;

import mrs.application.service.room.ReservableRoomRepository;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public ReservationService(ReservationRepository reservationRepository, ReservableRoomRepository reservableRoomRepository) {
        this.reservationRepository = reservationRepository;
        this.reservableRoomRepository = reservableRoomRepository;
    }

    /**
     * 会議室を予約する
     */
    public void reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.ReservableRoom().ReservableRoomId();
        // 悲観ロック
        Optional<ReservableRoom> reservable = Optional.ofNullable(reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId));
        if (reservable.isEmpty()) {
            throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。");
        }
        // 重複チェック
        boolean overlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId).stream().anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new AlreadyReservedException("入力の時間帯はすでに予約済です。");
        }
        // 予約情報の登録
        reservationRepository.save(reservation);
    }

    /**
     * 会議室の予約を取り消す
     */
    @PreAuthorize("hasRole('ADMIN') or #reservation.User.UserId == principal.user.UserId")
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
    public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
        return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
    }
}
