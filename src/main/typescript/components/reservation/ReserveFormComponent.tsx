import React, {useEffect, useState} from "react";
import "../../static/css/style.scss";
import {AppHeader} from "../share/AppHeaderComponent";
import {Navigate, useLocation, useNavigate} from "react-router-dom";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../../app/store";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {
    currentReservedDate,
    reservationCancel,
    reservationList,
    reservationReserve,
    reservationState,
    setParams
} from "../../features/reservation/reservationSlice";
import {currentUser} from "../../features/auth/authSlice";

export const ReserveForm: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const state = useSelector(reservationState);
    const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
    const user = useAppSelector(currentUser);
    if (!user) return <Navigate to="/login"/>;
    const {message} = useAppSelector(selectMessage);
    const reservedDate = useAppSelector(currentReservedDate);
    const location: any = useLocation();

    useEffect(() => {
        dispatch(clearMessage());
        dispatch(setParams({
            reservedDate: location.state.date,
            roomId: location.state.roomId,
            roomName: location.state.roomName
        }));
        list().then(r => (setSuccessful(true)));
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(reservationList({date: state.reservedDate, roomId: location.state.roomId}))
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

    const handleRooms = () => {
        navigate("/rooms");
    }

    const [startTime, setStartTime] = useState("09:00");
    const [endTime, setEndTime] = useState("10:00");
    const handleChangeStartTime = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setStartTime(e.target.value);
    }
    const handleChangeEndTime = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setEndTime(e.target.value);
    }

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        setSuccessful(false);

        const params = {
            date: new Date(reservedDate),
            start: startTime,
            end: endTime,
            roomId: state.roomId,
            userId: user.id
        }

        const resultAction = await dispatch(reservationReserve(params));
        if (reservationReserve.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handleCancel = async (e: any) => {
        if (!confirm("予約をキャンセルしますか？")) return;

        setSuccessful(false);
        const reservationId = e.target.dataset["reservationid"];
        const userId = e.target.dataset["userid"];

        const params = {
            date: new Date(reservedDate),
            roomId: state.roomId,
            reservationId: reservationId,
            userId: userId
        };
        const resultAction = await dispatch(reservationCancel(params));
        if (reservationCancel.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const showCancelButton = (params: { userId: string, reservationId: number }) => {
        if (user.userInfo?.userId.value === params.userId || user.userInfo?.roleName === '管理者')
            return (
                <button
                    onClick={handleCancel}
                    type="submit"
                    className="app-btn app-btn-accent reserve-form-table-button"
                    data-userId={params.userId}
                    data-reservationid={params.reservationId}
                >取消</button>
            )
    }

    return (
        <div>
            <AppHeader/>

            <section className="app">
                <div className="app-container">
                    <div className="message">
                        {successful ? (<p className="success">{message}</p>) : (<p className="error">{message}</p>)}
                    </div>

                    <div className="app-decoration">
                        <form className="app-form" onSubmit={e => {
                            e.preventDefault();
                            handleSubmit(e).then();
                        }}>
                            <a className="app-btn" onClick={handleRooms}>戻る</a>

                            <label>会議室:</label>
                            <span>{state.roomName}</span>
                            <label> 予約者名:</label>
                            <span>{user.userInfo?.name.firstName + ' ' + user.userInfo?.name.lastName}</span>
                            <label>日付:</label>
                            <span>{reservedDate}</span>
                            <label>開始時間:</label>
                            <select id="startTime" name="startTime" value={startTime} onChange={handleChangeStartTime}>
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
                            <select id="endTime" name="endTime" value={endTime} onChange={handleChangeEndTime}>
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


                            <button className="app-btn" type="submit">予約</button>
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
                                            {showCancelButton({
                                                userId: item.user.userId.value,
                                                reservationId: item.reservationId.value
                                            })}
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

