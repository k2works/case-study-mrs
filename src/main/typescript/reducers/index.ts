import {combineReducers} from "redux";
import counterReducer from "../features/counterSlice";

const rootReducer = combineReducers({
    counter: counterReducer,
});

export type RootState = ReturnType<typeof rootReducer>;

export default rootReducer;
