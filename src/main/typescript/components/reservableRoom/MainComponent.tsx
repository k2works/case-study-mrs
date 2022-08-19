import React, {useEffect, useRef, useState} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {useAppSelector} from "../../app/hook";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {
    reservableRoomCreate,
    reservableRoomDelete,
    reservableRoomList,
    reservableRoomState
} from "../../features/reservableRoom/reservableRoomSlice";
import {meetingRoomListBox, meetingRoomState} from "../../features/meetingRoom/meetingRoomSlice";
import {useInterval} from "../auth/LoginComponent";
import {PageNation} from "../share/PageNationComponent";

export const Main: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const reservableRoom = useAppSelector(reservableRoomState);
    const meetingRoom = useAppSelector(meetingRoomState);
    const [roomId, setRoomId] = useState(0);
    const [reservedDate, setReservedDate] = useState("");
    const registRef = useRef<HTMLDivElement>(null);
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState(".");

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
        listBox().then(r => (setSuccessful(true)));
    }, []);

    useInterval(() => {
        setCount(count + ".");
        if (count.length > 10) {
            setCount(".");
        }
    }, 500);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(reservableRoomList(1));
        if (reservableRoomList.fulfilled.match(resultAction)) {
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

    const handleRegistDialog = (e: any) => {
        e.preventDefault();
        setRoomId(0);
        setReservedDate("");

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.top = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleClose = (e: any) => {
        e.preventDefault();
        if (registRef.current) {
            registRef.current.style.display = 'none';
        }
    }

    const handleReservedDate = (e: any) => {
        setReservedDate(e.target.value);
    }

    const handleRoomId = (e: any) => {
        setRoomId(e.target.value);
    }

    const formatRoomId = (id: number) => {
        return id.toString().padStart(3, '0');
    }

    const handleRegist = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const params = {
            roomId,
            reservedDate
        }

        const resultAction = await dispatch(reservableRoomCreate(params));
        if (reservableRoomCreate.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.data.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handleDelete = async (e: any) => {
        if (!confirm("削除しますか？")) return;
        e.preventDefault();
        setSuccessful(false);
        const date = e.target.dataset["date"];
        const id = e.target.dataset["id"];

        const params = {
            roomId: id,
            reservedDate: date
        }

        const resultAction = await dispatch(reservableRoomDelete(params));
        if (reservableRoomDelete.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.data.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const listBox = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(meetingRoomListBox(1));
        if (meetingRoomListBox.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.data.message));
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

    const handlePageNation = async (e: any) => {
        setLoad(true);
        const page = e.target.dataset["page"];
        await dispatch(reservableRoomList({page: page}));
        setLoad(false);
    }

    return (
        <div>
            <AppHeader/>
            <div className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">
                        {load ? <div className="loading">{count}</div> : null}
                        {!load && reservableRoom.pageInfo.pages > 10 ? PageNation({
                            handler: handlePageNation,
                            pageInfo: reservableRoom.pageInfo
                        }) : <></>}
                    </div>

                    <div className="app-decoration">
                        <div className="message">
                            {successful ? (<p className="success">{message}</p>) : (<p className="error">{message}</p>)}
                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="regist_button" onClick={handleRegistDialog}>新規</button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>予約日</th>
                                <th>会議室番号</th>
                                <th>会議室名</th>
                            </tr>
                            {
                                reservableRoom.reservableRooms.list.map((item: any) => (
                                    <tr>
                                        <td>
                                            <span>{item.reservableRoomId.reservedDate.value}</span>
                                        </td>
                                        <td>
                                            <span>{formatRoomId(item.reservableRoomId.roomId.value)}</span>
                                        </td>
                                        <td>
                                            <span>{item.meetingRoom.roomName}</span>
                                        </td>
                                        <td>
                                            <button
                                                className="app-btn app-btn-accent"
                                                data-date={item.reservableRoomId.reservedDate.value}
                                                data-id={item.reservableRoomId.roomId.value}
                                                onClick={handleDelete}>
                                                削除
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form">
                                    <label>予約日</label>
                                    <input id="regist_reservedDate" name="reservedDate" type="date" value={reservedDate}
                                           onChange={handleReservedDate}/>
                                    <ul>

                                    </ul>
                                    <label>会議室</label>
                                    <select id="regist_id" name="roomId" value={roomId} onChange={handleRoomId}>
                                        <option value="">---</option>
                                        {
                                            Object.entries(meetingRoom.meetingRoomListBox).map(([key, value]: any) => (
                                                <option value={key}>{value}</option>
                                            ))
                                        }
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" onClick={handleRegist}>登録</button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
