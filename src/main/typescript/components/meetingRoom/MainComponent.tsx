import React, {useEffect, useRef, useState} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {
    meetingRoomCreate,
    meetingRoomDelete,
    meetingRoomList,
    meetingRoomState,
    meetingRoomUpdate
} from "../../features/meetingRoom/meetingRoomSlice";
import {useAppSelector} from "../../app/hook";
import {useInterval} from "../auth/LoginComponent";
import {PageNation} from "../share/PageNationComponent";

export const Main: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const room = useAppSelector(meetingRoomState);
    const [roomId, setRoomId] = useState(undefined);
    const [roomName, setRoomName] = useState("");
    const registRef = useRef<HTMLDivElement>(null);
    const updateRef = useRef<HTMLDivElement>(null);
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState(".");

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
        const resultAction = await dispatch(meetingRoomList(1));
        if (meetingRoomList.fulfilled.match(resultAction)) {
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

    const handleRegistDialog = (e: any) => {
        e.preventDefault();
        setRoomId(undefined);
        setRoomName("");

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.top = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateDialog = (e: any) => {
        e.preventDefault();
        const id = e.target.dataset["id"];
        const name = e.target.dataset["name"];
        setRoomId(id);
        setRoomName(name);

        if (updateRef.current) {
            updateRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.top = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.display = 'block';
        }
    }

    const handleClose = (e: any) => {
        e.preventDefault();
        if (registRef.current) {
            registRef.current.style.display = 'none';
        }
        if (updateRef.current) {
            updateRef.current.style.display = 'none';
        }
    }

    const handleRoomId = (e: any) => {
        setRoomId(e.target.value);
    }

    const handleRoomName = (e: any) => {
        setRoomName(e.target.value);
    }

    const formatRoomId = (id: number) => {
        return id.toString().padStart(3, '0');
    }

    const handleRegist = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const params = {
            roomId,
            roomName
        }

        const resultAction = await dispatch(meetingRoomCreate(params));
        if (meetingRoomCreate.fulfilled.match(resultAction)) {
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

    const handleUpdate = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const params = {
            roomId,
            roomName
        }

        const resultAction = await dispatch(meetingRoomUpdate(params));
        if (meetingRoomUpdate.fulfilled.match(resultAction)) {
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
        const id = e.target.dataset["id"];

        const params = {
            roomId: id,
            roomName: ""
        }

        const resultAction = await dispatch(meetingRoomDelete(params));
        if (meetingRoomDelete.fulfilled.match(resultAction)) {
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

    const handlePageNation = async (e: any) => {
        setLoad(true);
        const page = e.target.dataset["page"];
        await dispatch(meetingRoomList({page: page}));
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
                        {!load && room.pageInfo.pages > 10 ? PageNation({
                            handler: handlePageNation,
                            pageInfo: room.pageInfo
                        }) : <></>}
                    </div>

                    <div className="app-decoration">
                        <div className="message">
                            {successful ? (<p className="success">{message}</p>) : (<p className="error">{message}</p>)}
                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="regist_button" type="submit"
                                onClick={handleRegistDialog}>新規
                        </button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>会議室番号</th>
                                <th>会議室名</th>
                            </tr>
                            {
                                room.meetingRooms.list.map(item => (
                                    <tr>
                                        <td>
                                            <span>{formatRoomId(item.roomId.value)}</span>
                                        </td>
                                        <td>
                                            <span>{item.roomName}</span>
                                        </td>
                                        <td>
                                            <button
                                                className="app-btn"
                                                data-id={item.roomId.value}
                                                data-name={item.roomName}
                                                onClick={handleUpdateDialog}>
                                                編集
                                            </button>
                                        </td>
                                        <td>
                                            <a className="app-btn app-btn-accent"
                                               data-id={item.roomId.value}
                                               onClick={handleDelete}>
                                                削除
                                            </a>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form">
                                    <label>会議室番号</label>
                                    <input id="regist_id" name="roomId" type="text" value={roomId}
                                           onChange={handleRoomId}/>
                                    <ul>

                                    </ul>
                                    <label>会議室名</label>
                                    <input id="regist_roomName" name="roomName" type="text" value={roomName}
                                           onChange={handleRoomName}/>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" onClick={handleRegist}>登録
                                    </button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form">
                                    <label>会議室番号</label>
                                    <input id="update_id" name="roomId" readOnly={true} type="text" value={roomId}/>
                                    <ul>

                                    </ul>
                                    <label>会議室名</label>
                                    <input id="update_roomName" name="roomName" type="text" value={roomName}
                                           onChange={handleRoomName}/>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="update" type="submit" onClick={handleUpdate}>登録
                                    </button>
                                    <button className="app-btn app-btn-accent" type="reset" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
