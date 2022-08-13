package mrs.presentation.api.room;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomList;

@Schema(description = "予約可能会議室リソース")
public class RoomsResource {
    private ReservableRoomList rooms;
    private PageInfo<ReservableRoom> pageInfo;

    public RoomsResource(ReservableRoomList rooms, PageInfo<ReservableRoom> pageInfo) {
        this.rooms = rooms;
        this.pageInfo = pageInfo;
    }
}
