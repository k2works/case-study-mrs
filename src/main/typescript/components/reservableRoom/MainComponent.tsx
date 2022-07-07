import React from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";

export const Main: React.FC<{}> = () => {
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
                        <button className="regist app-btn" id="regist_button" type="submit">新規</button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>予約日</th>
                                <th>会議室番号</th>
                                <th>会議室名</th>
                            </tr>
                            <tr>
                                <td>
                                    <span>2022-07-07</span></td>
                                <td>
                                    <span>001</span></td>
                                <td>
                                    <span>新木場</span></td>
                                <td>
                                    <a className="app-btn app-btn-accent"
                                       href="/reservableRooms/delete/1/2022-07-07">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>2022-07-07</span></td>
                                <td>
                                    <span>007</span></td>
                                <td>
                                    <span>有楽町</span></td>
                                <td>
                                    <a className="app-btn app-btn-accent"
                                       href="/reservableRooms/delete/7/2022-07-07">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>2022-07-08</span></td>
                                <td>
                                    <span>001</span></td>
                                <td>
                                    <span>新木場</span></td>
                                <td>
                                    <a className="app-btn app-btn-accent"
                                       href="/reservableRooms/delete/1/2022-07-08">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>2022-07-08</span></td>
                                <td>
                                    <span>007</span></td>
                                <td>
                                    <span>有楽町</span></td>
                                <td>
                                    <a className="app-btn app-btn-accent"
                                       href="/reservableRooms/delete/7/2022-07-08">削除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div id="registDialog">
                            <div>
                                <form className="app-form" method="post" action="/reservableRooms">
                                    <label>予約日</label>
                                    <input id="regist_reservedDate" name="reservedDate" type="date" value=""/>
                                    <ul>

                                    </ul>
                                    <label>会議室</label>
                                    <select id="regist_id" name="roomId">
                                        <option value="">---</option>
                                        <option value="1">新木場</option>
                                        <option value="2">辰巳</option>
                                        <option value="3">豊洲</option>
                                        <option value="4">月島</option>
                                        <option value="5">新富町</option>
                                        <option value="6">銀座一丁目</option>
                                        <option value="7">有楽町</option>
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" type="submit">登録</button>
                                    <button className="app-btn app-btn-accent" type="reset">キャンセル</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
