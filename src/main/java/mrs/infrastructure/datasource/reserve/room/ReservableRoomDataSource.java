package mrs.infrastructure.datasource.reserve.room;

import org.springframework.stereotype.Repository;

@Repository
public class ReservableRoomDataSource {
    ReservableRoomMapper reservableRoomMapper;

    public ReservableRoomDataSource(ReservableRoomMapper reservableRoomMapper) {
        this.reservableRoomMapper = reservableRoomMapper;
    }
}
