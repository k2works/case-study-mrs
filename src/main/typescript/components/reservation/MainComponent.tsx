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
import {useInterval} from "../auth/LoginComponent";
import {PageNation} from "../share/PageNationComponent";

export const Main: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState("...");

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
    }, []);

    useInterval(() => {
        setCount(count + ".");
        if (count.length > 10) {
            setCount(".");
        }
    }, 500);

    const list = async () => {
        setSuccessful(false);
        setLoad(true);
        const resultAction = await dispatch(roomList({reservedDate: new Date()}));
        if (roomList.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
            setLoad(false);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
            setLoad(false);
        }
    }

    const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
    const {message} = useAppSelector(selectMessage);
    const room = useAppSelector(roomState);
    const currentDay = useAppSelector(currentReservedDate);

    const handlePreDay = async (e: any) => {
        setLoad(true);
        dispatch(decrementReservedDate(e.target.value));
        const current = new Date(currentDay);
        const preDay = new Date(current.setDate(current.getDate() - 1));
        await dispatch(roomList({reservedDate: preDay}));
        setLoad(false);
    }

    const handleNextDay = async (e: any) => {
        setLoad(true);
        dispatch(incrementReservedDate(e.target.value));
        const current = new Date(currentDay);
        const nextDay = new Date(current.setDate(current.getDate() + 1));
        await dispatch(roomList({reservedDate: nextDay}));
        setLoad(false);
    }

    const handleReservableRoom = (e: any) => {
        const date = e.target.dataset["date"];
        const id = e.target.dataset["id"];
        const name = e.target.dataset["name"];
        navigate(`/reservations?page=${date}-${id}`, {state: {date: date, roomId: id, roomName: name}});
    }

    const handlePageNation = async (e: any) => {
        setLoad(true);
        const current = new Date(currentDay);
        const page = e.target.dataset["page"];
        await dispatch(roomList({reservedDate: current, page: page}));
        setLoad(false);
    }

    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>
                    <div className="app-decoration">
                        {load ? <div className="loading">{count}</div> : null}
                        {!load && room.pageInfo.pages > 10 ? PageNation({
                            handler: handlePageNation,
                            pageInfo: room.pageInfo
                        }) : <></>}
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
                                    <li id={`No${item.reservableRoomId.roomId.value}`}>
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
