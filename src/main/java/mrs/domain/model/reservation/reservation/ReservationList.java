package mrs.domain.model.reservation.reservation;

import java.util.List;

/**
 * 予約一覧
 */
public class ReservationList {
    List<Reservation> list;

    public ReservationList() {
        this.list = null;
    }

    public ReservationList(List<Reservation> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public List<Reservation> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "ReservationList{" +
                "list=" + list +
                '}';
    }
}
