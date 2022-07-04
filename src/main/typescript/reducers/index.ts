import {combineReducers} from "redux";
import authReducer from '../features/auth/authSlice'
import messageReducer from '../features/message/messageSlice'
import roomReducer from '../features/room/roomSlice'

const rootReducer = combineReducers({
    auth: authReducer,
    message: messageReducer,
    room: roomReducer
});

export default rootReducer;
