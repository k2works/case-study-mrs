import React, {useRef} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";

export const Main: React.FC<{}> = () => {
    const registRef = useRef<HTMLDivElement>(null);
    const updateRef = useRef<HTMLDivElement>(null);

    const handleRegistMeetingRoom = () => {
        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateMeetingRoom = () => {
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


                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="regist_button" type="submit"
                                onClick={handleRegistMeetingRoom}>新規
                        </button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>会議室番号</th>
                                <th>会議室名</th>
                            </tr>
                            <tr>
                                <td>
                                    <span>001</span></td>
                                <td>
                                    <span>新木場</span></td>
                                <td>
                                    <button className="app-btn" type="submit" onClick={handleUpdateMeetingRoom}>編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent" href="/meetingRooms/delete/1">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>002</span></td>
                                <td>
                                    <span>辰巳</span></td>
                                <td>
                                    <button className="app-btn" type="submit" onClick={handleUpdateMeetingRoom}>編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent" href="/meetingRooms/delete/2">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>003</span></td>
                                <td>
                                    <span>豊洲</span></td>
                                <td>
                                    <button className="app-btn" type="submit" onClick={handleUpdateMeetingRoom}>編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent" href="/meetingRooms/delete/3">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>004</span></td>
                                <td>
                                    <span>月島</span></td>
                                <td>
                                    <button className="app-btn" type="submit" onClick={handleUpdateMeetingRoom}>編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent" href="/meetingRooms/delete/4">削除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form" method="post" action="/meetingRooms">
                                    <label>会議室番号</label>
                                    <input id="regist_id" name="roomId" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>会議室名</label>
                                    <input id="regist_roomName" name="roomName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" type="submit">登録</button>
                                    <button className="app-btn app-btn-accent" type="reset" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form" method="post" action="/meetingRooms">
                                    <label>会議室番号</label>
                                    <input id="update_id" name="roomId" readOnly={true} type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>会議室名</label>
                                    <input id="update_roomName" name="roomName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="update" type="submit">登録</button>
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
