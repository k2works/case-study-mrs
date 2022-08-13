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

export const Main: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(roomList({reservedDate: new Date()}));
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
        navigate(`/reservations?page=${date}-${id}`, {state: {date: date, roomId: id, roomName: name}});
    }

    const handlePageNation = async (e: any) => {
        const current = new Date(currentDay);
        const page = e.target.dataset["page"];
        await dispatch(roomList({reservedDate: current, page: page}));
    }

    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">
                        <nav>
                            <ul className="pagination">
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={1}
                                    >
                                        最初
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pageNum - 1}
                                    >
                                        前へ
                                    </a>
                                </li>


                                <li className="active">
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pageNum}
                                    >
                                        {room.pageInfo.pageNum}
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pageNum + 1}
                                    >
                                        {room.pageInfo.pageNum + 1}
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pageNum + 2}
                                    >
                                        {room.pageInfo.pageNum + 2}
                                    </a>
                                </li>
                                <li>...</li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pages - 2}
                                    >
                                        {room.pageInfo.pages - 2}
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pages - 1}
                                    >
                                        {room.pageInfo.pages - 1}
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pages}
                                    >
                                        {room.pageInfo.pages}
                                    </a>
                                </li>

                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pageNum + 1}
                                    >
                                        次へ
                                    </a>
                                </li>
                                <li>
                                    <a onClick={handlePageNation}
                                       data-page={room.pageInfo.pages}
                                    >
                                        最後
                                    </a>
                                </li>
                            </ul>
                        </nav>
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
