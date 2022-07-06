import React from "react";
import "../../static/css/style.scss";
import {currentUser} from "../../features/auth/authSlice";
import {useAppSelector} from "../../app/hook";
import {Navigate} from "react-router-dom";

export const AppMenu: React.FC<{}> = () => {
    const user = useAppSelector(currentUser);
    if (!user) return <Navigate to="/login"/>;

    return (
        <div>
            <div className="app-decoration">
                <div>
                    <div className="app-nav" id="header-nav">
                        <ul className="header-list">
                            <li className="menu-item a">
                                <a href="">予約</a>
                                <ul className="sub-menu">
                                    <li className="sub-menu-item a"><a href="/rooms">会議室予約一覧</a></li>
                                    {user.userInfo?.roleName === "管理者" && (
                                        <li className="sub-menu-item a"><a href="/meeting_rooms">会議室一覧</a></li>
                                    )}
                                </ul>
                            </li>
                            <li className="menu-item b">
                                <a href="">会員</a>
                                <ul className="sub-menu">
                                    <li className="sub-menu-item b"><a href="/contact_regist">問い合わせ</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
