import React, {useEffect, useState} from "react";
import "../../static/css/style.scss";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useNavigate} from "react-router-dom";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../../app/store";
import {
    currentReservedDate,
    decrementReservedDate,
    incrementReservedDate,
    roomList,
    roomState
} from "../../features/room/roomSlice";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {setParams} from "../../features/reservation/reservationSlice";

export const Main: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);

    useEffect(() => {
        dispatch(clearMessage());
        list();
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(roomList(new Date()));
        if (roomList.fulfilled.match(resultAction)) {
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
    const room = useAppSelector(roomState);
    const currentDay = useAppSelector(currentReservedDate);

    const handlePreDay = async (e: any) => {
        dispatch(decrementReservedDate(e.target.value));
        const current = new Date(currentDay);
        const preDay = new Date(current.setDate(current.getDate() - 1));
        await dispatch(roomList(preDay));
    }

    const handleNextDay = async (e: any) => {
        dispatch(incrementReservedDate(e.target.value));
        const current = new Date(currentDay);
        const nextDay = new Date(current.setDate(current.getDate() + 1));
        await dispatch(roomList(nextDay));
    }

    const handleReservableRoom = (e: any) => {
        const date = e.target.dataset["date"];
        const id = e.target.dataset["id"];
        const name = e.target.dataset["name"];
        dispatch(setParams({reservedDate: date, roomId: id, roomName: name}));
        navigate(`/reservations?page=${date}-${id}`);
    }

    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">

                    </div>

                    <div className="app-decoration">
                        <a onClick={handlePreDay}>&lt; 前日</a>
                        <span>{currentDay} の会議室</span>
                        <a onClick={handleNextDay}>翌日 &gt;</a>
                    </div>

                    <div className="app-decoration">
                        <ul>
                            {
                                room.reservableRooms.list.map(item => (
                                    <li>
                                        <a onClick={handleReservableRoom}
                                           data-date={item.reservableRoomId.reservedDate.value}
                                           data-id={item.reservableRoomId.roomId.value}
                                           data-name={item.meetingRoom.roomName}
                                        >
                                            {item.meetingRoom.roomName}
                                        </a>
                                    </li>
                                ))
                            }
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    )
}
