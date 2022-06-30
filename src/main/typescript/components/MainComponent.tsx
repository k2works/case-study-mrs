import * as React from "react";
import "../static/css/style.scss";
import Logo from "../static/img/logo.svg";
import Tool from "../static/img/tool.jpg";

export const Main: React.FC<{}> = () => {
    return (
        <div>
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

            <section className="hero">
                <div className="hero-container w-container">
                    <h1>Stationery Service</h1>
                    <p>便利な道具とサービスをお届けします</p>
                    <a href="#" className="btn">
                        無料で始める
                    </a>
                </div>
            </section>

            <section className="imgtext">
                <div className="imgtext-container w-container">
                    <div className="text">
                        <h2 className="heading-decoration">日常のツールたち</h2>
                        <p>Convenient</p>
                        <p>
                            どこにでもある、誰でも使ったことがある、普段は存在を意識しないけどないと困るツールたち。日常をちょっと楽にしてくれます。
                        </p>
                        <figure className="img">
                            <img src={Tool} alt="" width={1600} height={1260}/>
                        </figure>
                    </div>
                </div>
            </section>
        </div>
    );
};
