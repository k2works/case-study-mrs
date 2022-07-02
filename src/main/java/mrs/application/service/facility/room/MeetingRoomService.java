package mrs.application.service.facility.room;

import mrs.application.service.user.UserAlreadyRegistException;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.facility.room.RoomId;
import mrs.infrastructure.datasource.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 会議室の登録
 */
@Service
@Transactional
public class MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;

    private final Message message;

    public MeetingRoomService(MeetingRoomRepository meetingRoomRepository, Message message) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.message = message;
    }

    /**
     * 会議室を検索する
     */
    public MeetingRoom findMeetingRoom(RoomId roomId) {
        return meetingRoomRepository.getById(roomId);
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
     * 会議室リストボックスを作成する
     */
    public Map<Integer, String> createRoomNameMap() {
        List<MeetingRoom> meetingRooms = meetingRoomRepository.findAll();
        Map<Integer, String> roomNameMap = new LinkedHashMap<Integer, String>();
        meetingRooms.forEach(meetingRoom -> {
            roomNameMap.put(meetingRoom.RoomId().Value(), meetingRoom.RoomName());
        });
        return roomNameMap;
    }
}
