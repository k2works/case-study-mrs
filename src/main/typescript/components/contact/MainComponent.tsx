import React, {useRef} from "react";
import {AppMenu} from "../share/AppMenuComponent";
import {AppHeader} from "../share/AppHeaderComponent";

export const Main: React.FC<{}> = () => {
    const updateRef = useRef<HTMLDivElement>(null);

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
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>問い合わせ番号</th>
                                <th>問い合わせ内容</th>
                                <th>利用者区分</th>
                            </tr>
                            <tr>
                                <td>
                                    <span>1f0d543...</span>
                                </td>
                                <td>
                                    <span>あああああああああああああああああ...</span>
                                </td>
                                <td>
                                    <span>ゲスト</span>
                                </td>
                                <td>
                                    <button className="app-btn" onClick={handleUpdateDialog}>詳細</button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>80e9bb8...</span></td>
                                <td>
                                    <span>string</span></td>
                                <td>
                                    <span>ゲスト</span></td>
                                <td>
                                    <button className="app-btn" onClick={handleUpdateDialog}>詳細</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div id="updateDialog" ref={updateRef}>
                        <div>
                            <form className="app-form">
                                <label>問い合わせ番号</label>
                                <input id="update_id" name="contactId" readOnly={true} type="text" value=""/>
                                <ul>

                                </ul>
                                <label>問い合わせ内容</label>
                                <textarea id="update_details" name="details" readOnly={true}></textarea>
                                <ul>

                                </ul>
                                <button className="app-btn app-btn-accent" onClick={handleClose}>閉じる</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
