import React from "react";
import "../../static/css/style.scss";
import {currentUser} from "../../features/auth/authSlice";
import {useAppSelector} from "../../app/hook";
import {Navigate, useNavigate} from "react-router-dom";

export const AppMenu: React.FC<{}> = () => {
    const navigate = useNavigate();
    const user = useAppSelector(currentUser);
    if (!user) return <Navigate to="/login"/>;

    const handleOnClick = (e: any) => {
        const href = e.target.dataset.href;
        navigate(href);
    }

    return (
        <div>
            <div className="app-decoration">
                <div>
                    <div className="app-nav" id="header-nav">
                        <ul className="header-list">
                            <li className="menu-item a">
                                <a href="">予約</a>
                                <ul className="sub-menu">
                                    <li className="sub-menu-item a"><a onClick={handleOnClick} data-href={"/rooms"} >会議室予約一覧</a></li>
                                    {user.userInfo?.roleName === "管理者" && (
                                        <>
                                            <li className="sub-menu-item a"><a  onClick={handleOnClick} data-href="/meeting_rooms">会議室一覧</a></li>
                                            <li className="sub-menu-item a"><a  onClick={handleOnClick} data-href="/reservable_meeting_rooms">予約可能会議室一覧</a></li>
                                        </>
                                    )}
                                </ul>
                            </li>
                            <li className="menu-item b">
                                <a href="">会員</a>
                                <ul className="sub-menu">
                                    {user.userInfo?.roleName === "管理者" && (
                                        <>
                                            <li className="sub-menu-item b"><a onClick={handleOnClick} data-href="/users">利用者一覧</a></li>
                                            <li className="sub-menu-item b"><a onClick={handleOnClick} data-href="/contacts">問い合わせ一覧</a></li>
                                        </>
                                    )}
                                    {user.userInfo?.roleName === "一般" && (
                                        <>
                                            <li className="sub-menu-item b"><a onClick={handleOnClick} data-href="/contacts_member_regist">問い合わせ</a></li>
                                        </>
                                    )}
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
