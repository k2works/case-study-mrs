import React from "react";
import Logo from "../../static/img/logo.svg";
import "../../static/css/style.scss";

export const AppHeader: React.FC<{}> = () => {
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
                                <form method="get" name="logout_form" action="/">
                                    <a href="/">ログアウト</a>
                                </form>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>
        </div>
    )
}
