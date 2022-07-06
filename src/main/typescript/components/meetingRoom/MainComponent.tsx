import React, {useEffect, useRef, useState} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {meetingRoomList, meetingRoomState} from "../../features/meetingRoom/meetingRoomSlice";
import {useAppSelector} from "../../app/hook";

export const Main: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const room = useAppSelector(meetingRoomState);
    const [roomId, setRoomId] = useState(1);
    const [roomName, setRoomName] = useState("");
    const registRef = useRef<HTMLDivElement>(null);
    const updateRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(meetingRoomList(1));
        if (meetingRoomList.fulfilled.match(resultAction)) {
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

    const handleRegistDialog = () => {
        setRoomId(0);
        setRoomName("");

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateDialog = (e: any) => {
        const id = e.target.dataset["id"];
        const name = e.target.dataset["name"];
        setRoomId(id);
        setRoomName(name);

        if (updateRef.current) {
            updateRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.display = 'block';
        }
    }

    const handleClose = () => {
        if (registRef.current) {
            registRef.current.style.display = 'none';
        }
        if (updateRef.current) {
            updateRef.current.style.display = 'none';
        }
    }

    const formatRoomId = (id: number) => {
        return id.toString().padStart(3, '0');
    }

    function handleRegist() {

    }

    function handleUpdate() {

    }

    function handleDelete() {

    }

    function handleRoomId(e: any) {
        setRoomId(e.target.value);
    }

    function handleRoomName(e: any) {
        setRoomName(e.target.value);
    }

    return (
        <div>
            <AppHeader/>
            <div className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">

                    </div>

                    <div className="app-decoration">
                        <div className="message">
                            {!successful && (<p className="error">{message}</p>)}
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
                                            <a className="app-btn app-btn-accent" onClick={handleDelete}>削除</a>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form" method="post" action="/meetingRooms">
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
                                    <button className="app-btn" name="regist" type="submit" onClick={handleRegist}>登録
                                    </button>
                                    <button className="app-btn app-btn-accent" type="reset" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form" method="post" action="/meetingRooms">
                                    <label>会議室番号</label>
                                    <input id="update_id" name="roomId" readOnly={true} type="text" value={roomId}/>
                                    <ul>

                                    </ul>
                                    <label>会議室名</label>
                                    <input id="update_roomName" name="roomName" type="text" value={roomName}/>
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
