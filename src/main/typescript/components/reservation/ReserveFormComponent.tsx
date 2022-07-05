import React, {useEffect, useState} from "react";
import "../../static/css/style.scss";
import {AppHeader} from "../share/AppHeaderComponent";
import {useNavigate} from "react-router-dom";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../../app/store";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {reservationList, reservationState} from "../../features/reservation/reservationSlice";
import {currentUser} from "../../features/auth/authSlice";

export const ReserveForm: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const state = useSelector(reservationState);

    useEffect(() => {
        dispatch(clearMessage());
        list();
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(reservationList({date: state.reservedDate, roomId: state.roomId}))
        if (reservationList.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }
    const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
    const {message} = useAppSelector(selectMessage);

    const user = useAppSelector(currentUser);
    const showCancelButton = (userId: string) => {
        if (user?.userId === userId) return <button onClick={() => {
        }} type="submit">取消</button>
    }

    return (
        <div>
            <AppHeader/>

            <section className="app">
                <div className="app-container">
                    <div className="message">

                    </div>

                    <div className="app-decoration">
                        <form className="app-form" method="post" action="/reservations/2022-06-30/1">
                            <input type="hidden" name="_csrf" value="cf4c970c-b56c-46cd-872c-9268d01b5085"/>
                            <a className="app-btn" href="/rooms">戻る</a>

                            <label>会議室:</label>
                            <span>新木場</span>
                            <label> 予約者名:</label>
                            <span>Aaa Aaa</span>
                            <label>日付:</label>
                            <span>2022/6/30</span>
                            <label>開始時間:</label>
                            <select id="startTime" name="startTime">
                                <option value="00:00">00:00</option>
                                <option value="00:30">00:30</option>
                                <option value="01:00">01:00</option>
                                <option value="01:30">01:30</option>
                                <option value="02:00">02:00</option>
                                <option value="02:30">02:30</option>
                                <option value="03:00">03:00</option>
                                <option value="03:30">03:30</option>
                                <option value="04:00">04:00</option>
                                <option value="04:30">04:30</option>
                                <option value="05:00">05:00</option>
                                <option value="05:30">05:30</option>
                                <option value="06:00">06:00</option>
                                <option value="06:30">06:30</option>
                                <option value="07:00">07:00</option>
                                <option value="07:30">07:30</option>
                                <option value="08:00">08:00</option>
                                <option value="08:30">08:30</option>
                                <option value="09:00" selected={true}>09:00</option>
                                <option value="09:30">09:30</option>
                                <option value="10:00">10:00</option>
                                <option value="10:30">10:30</option>
                                <option value="11:00">11:00</option>
                                <option value="11:30">11:30</option>
                                <option value="12:00">12:00</option>
                                <option value="12:30">12:30</option>
                                <option value="13:00">13:00</option>
                                <option value="13:30">13:30</option>
                                <option value="14:00">14:00</option>
                                <option value="14:30">14:30</option>
                                <option value="15:00">15:00</option>
                                <option value="15:30">15:30</option>
                                <option value="16:00">16:00</option>
                                <option value="16:30">16:30</option>
                                <option value="17:00">17:00</option>
                                <option value="17:30">17:30</option>
                                <option value="18:00">18:00</option>
                                <option value="18:30">18:30</option>
                                <option value="19:00">19:00</option>
                                <option value="19:30">19:30</option>
                                <option value="20:00">20:00</option>
                                <option value="20:30">20:30</option>
                                <option value="21:00">21:00</option>
                                <option value="21:30">21:30</option>
                                <option value="22:00">22:00</option>
                                <option value="22:30">22:30</option>
                                <option value="23:00">23:00</option>
                                <option value="23:30">23:30</option>
                            </select>

                            <label>終了時間:</label>
                            <select id="endTime" name="endTime">
                                <option value="00:00">00:00</option>
                                <option value="00:30">00:30</option>
                                <option value="01:00">01:00</option>
                                <option value="01:30">01:30</option>
                                <option value="02:00">02:00</option>
                                <option value="02:30">02:30</option>
                                <option value="03:00">03:00</option>
                                <option value="03:30">03:30</option>
                                <option value="04:00">04:00</option>
                                <option value="04:30">04:30</option>
                                <option value="05:00">05:00</option>
                                <option value="05:30">05:30</option>
                                <option value="06:00">06:00</option>
                                <option value="06:30">06:30</option>
                                <option value="07:00">07:00</option>
                                <option value="07:30">07:30</option>
                                <option value="08:00">08:00</option>
                                <option value="08:30">08:30</option>
                                <option value="09:00">09:00</option>
                                <option value="09:30">09:30</option>
                                <option value="10:00" selected={true}>10:00</option>
                                <option value="10:30">10:30</option>
                                <option value="11:00">11:00</option>
                                <option value="11:30">11:30</option>
                                <option value="12:00">12:00</option>
                                <option value="12:30">12:30</option>
                                <option value="13:00">13:00</option>
                                <option value="13:30">13:30</option>
                                <option value="14:00">14:00</option>
                                <option value="14:30">14:30</option>
                                <option value="15:00">15:00</option>
                                <option value="15:30">15:30</option>
                                <option value="16:00">16:00</option>
                                <option value="16:30">16:30</option>
                                <option value="17:00">17:00</option>
                                <option value="17:30">17:30</option>
                                <option value="18:00">18:00</option>
                                <option value="18:30">18:30</option>
                                <option value="19:00">19:00</option>
                                <option value="19:30">19:30</option>
                                <option value="20:00">20:00</option>
                                <option value="20:30">20:30</option>
                                <option value="21:00">21:00</option>
                                <option value="21:30">21:30</option>
                                <option value="22:00">22:00</option>
                                <option value="22:30">22:30</option>
                                <option value="23:00">23:00</option>
                                <option value="23:30">23:30</option>
                            </select>


                            <button className="app-btn">予約</button>
                        </form>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>時間帯</th>
                                <th>予約者</th>
                            </tr>
                            {
                                state.reservations.list.map(item => (
                                    <tr>
                                        <td>
                                            <span>{item.reservedTime.startTime}</span></td>
                                        <td>
                                            <span>{item.user.name.firstName}</span></td>
                                        <td>
                                            {showCancelButton(item.user.userId.value)}
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    )
}

