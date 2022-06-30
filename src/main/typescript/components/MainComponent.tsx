import * as React from "react";
import "../static/css/style.scss";
import Logo from "../static/img/logo.svg";

export const Main: React.FC<{}> = () => {
    return (
        <header className="header">
            <div className="header-container w-container">
                <div className="site">
                    <a href="/">
                        <img src={Logo} alt="" width={135} height={26}/>
                    </a>
                </div>

                <button className="navbtn">
                    <i className="fas fa-bars"></i>
                    <span className="sr-only">MENU</span>
                </button>
            </div>
        </header>
    );
};
