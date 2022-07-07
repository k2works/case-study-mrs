import {combineReducers} from "redux";
import authReducer from '../features/auth/authSlice'
import messageReducer from '../features/message/messageSlice'
import roomReducer from '../features/room/roomSlice'
import reservationReducer from '../features/reservation/reservationSlice'
import meetingRoomReducer from '../features/meetingRoom/meetingRoomSlice'
import reservableRoomReducer from '../features/reservableRoom/reservableRoomSlice'

const rootReducer = combineReducers({
    auth: authReducer,
    message: messageReducer,
    room: roomReducer,
    reservation: reservationReducer,
    meetingRoom: meetingRoomReducer,
    reservableRoom: reservableRoomReducer
});

export default rootReducer;
