package mrs.domain.model.reservation.room;

import java.util.List;

/**
 * 予約可能会議室一覧
 */
public class ReservableRoomList {
    private List<ReservableRoom> list;

    public ReservableRoomList() {
        this.list = null;
    }

    public ReservableRoomList(List<ReservableRoom> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public List<ReservableRoom> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "ReservableRoomList{" +
                "list=" + list +
                '}';
    }
}
