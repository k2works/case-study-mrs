import {combineReducers} from "redux";
import authReducer from '../features/auth/authSlice'
import messageReducer from '../features/message/messageSlice'

const rootReducer = combineReducers({
    auth: authReducer,
    message: messageReducer
});

export default rootReducer;
