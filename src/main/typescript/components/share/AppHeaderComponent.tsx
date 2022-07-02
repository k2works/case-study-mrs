import React from "react";
import Logo from "../../static/img/logo.svg";
import "../../static/css/style.scss";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import type {AppDispatch} from '../../app/store';
import {currentUser, logout} from "../../features/auth/authSlice";

export const AppHeader: React.FC<{}> = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();
    const user = useSelector(currentUser);

    const handleOnClickLogout = () => {
        dispatch(logout());
        navigate("/login");
    }

    return (
        <div>
            <header className="header">
                <div className="header-container w-container">
                    <div className="site">
                        <a href="#">
                            <img alt="Boards" height="26" src={Logo} width="135"/>
                        </a>
                    </div>

                    <button className="navbtn">
                        <i className="fas fa-bars"></i>
                        <i className="fas fa-times"></i>
                        <span className="sr-only">MENU</span>
                    </button>

                    <nav className="nav">
                        <ul>
                            <li>
                                <form method="get" name="logout_form">
                                    <a onClick={handleOnClickLogout}>ログアウト</a>
                                </form>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>
        </div>
    )
}
