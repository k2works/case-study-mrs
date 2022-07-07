import React, {useRef} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";

export const Main: React.FC<{}> = () => {
    const registRef = useRef<HTMLDivElement>(null);
    const updateRef = useRef<HTMLDivElement>(null);

    const handleRegistDialog = (e: any) => {
        e.preventDefault();

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.top = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateDialog = (e: any) => {
        e.preventDefault();

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

    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">

                    </div>

                    <div className="app-decoration">
                        <div className="message">

                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="update_button" onClick={handleRegistDialog}>新規</button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>利用者番号</th>
                                <th>姓</th>
                                <th>名</th>
                                <th>役割</th>
                            </tr>
                            <tr>
                                <td>
                                    <span>U000001</span></td>
                                <td>
                                    <span>Aaa</span></td>
                                <td>
                                    <span>Aaa</span></td>
                                <td>
                                    <span>一般</span></td>
                                <td>
                                    <button className="app-btn" onClick={handleUpdateDialog}>
                                        編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>U000002</span></td>
                                <td>
                                    <span>Bbb</span></td>
                                <td>
                                    <span>Bbb</span></td>
                                <td>
                                    <span>一般</span></td>
                                <td>
                                    <button className="app-btn">
                                        編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>U000003</span></td>
                                <td>
                                    <span>Ccc</span></td>
                                <td>
                                    <span>Ccc</span></td>
                                <td>
                                    <span>管理者</span></td>
                                <td>
                                    <button className="app-btn">
                                        編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>U000004</span></td>
                                <td>
                                    <span>山田</span></td>
                                <td>
                                    <span>太郎</span></td>
                                <td>
                                    <span>一般</span></td>
                                <td>
                                    <button className="app-btn">
                                        編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent">削除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>U999999</span></td>
                                <td>
                                    <span></span></td>
                                <td>
                                    <span></span></td>
                                <td>
                                    <span>ゲスト</span></td>
                                <td>
                                    <button className="app-btn">
                                        編集
                                    </button>
                                </td>
                                <td>
                                    <a className="app-btn app-btn-accent">削除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form">
                                    <label>利用者番号</label>
                                    <input id="regist_id" name="userId" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>姓</label>
                                    <input id="regist_firstName" name="firstName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>名</label>
                                    <input id="regist_lastName" name="lastName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>パスワード</label>
                                    <input id="regist_password" name="password" type="password" value=""/>
                                    <ul>

                                    </ul>
                                    <label>役割</label>
                                    <select id="regist_role" name="roleName">
                                        <option value="一般">一般</option>
                                        <option value="管理者">管理者</option>
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist">登録</button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form">
                                    <label>利用者番号</label>
                                    <input id="update_id" name="userId" readOnly={true} type="text" value=""/>
                                    <label>姓</label>
                                    <input id="update_firstName" name="firstName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>名</label>
                                    <input id="update_lastName" name="lastName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>パスワード</label>
                                    <input id="update_password" name="password" type="password" value=""/>
                                    <ul>
                                        <li>未入力の場合はパスワード未更新</li>

                                    </ul>
                                    <label>役割</label>
                                    <select id="update_role" name="roleName">
                                        <option value="一般">一般</option>
                                        <option value="管理者">管理者</option>
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="update">登録</button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}
