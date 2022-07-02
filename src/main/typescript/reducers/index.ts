import {combineReducers} from "redux";
import counterReducer from "../features/counterSlice";
import authReducer from '../features/auth/authSlice'
import messageReducer from '../features/message/messageSlice'

const rootReducer = combineReducers({
    counter: counterReducer,
    auth: authReducer,
    message: messageReducer
});

export type RootState = ReturnType<typeof rootReducer>;

export default rootReducer;
