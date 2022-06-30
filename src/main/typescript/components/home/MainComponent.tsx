import * as React from "react";
import "../../static/css/style.scss";
import {Header} from "./HeaderComponent";
import {Footer} from "./FooterComponent";
import Search from "../../static/img/search.png";
import Reserved from "../../static/img/reserved.png";

export const Main: React.FC<{}> = () => {
    return (
        <div>
            <Header/>

            <section className="hero">
                <div className="hero-container w-container">
                    <h1>Meeting Room Reservation System</h1>
                    <p>会議室予約システム</p>
                    <a className="btn" href="/login">ログイン</a>
                </div>
            </section>

            <section className="imgtext">
                <div className="imgtext-container w-container">
                    <div className="text">
                        <h2 className="heading-decoration">Webから会議室検索</h2>
                        <p>ReservableRoom</p>
                        <p>利用可能な会議室があるかをWebで検索できます</p>
                    </div>
                    <figure className="img">
                        <img alt="" height="1260" src={Search} width="1600"/>
                    </figure>
                </div>
            </section>

            <section className="imgtext">
                <div className="imgtext-container reverse w-container">
                    <div className="text">
                        <h2 className="heading-decoration">Webから会議室予約</h2>
                        <p>Reservation</p>
                        <p>利用可能な会議室をWebで予約できます</p>
                    </div>
                    <figure className="img">
                        <img alt="" height="1260" src={Reserved} width="1600"/>
                    </figure>
                </div>
            </section>

            <section className="posts">
                <div className="w-container">
                    <h2 className="heading">
                        News
                    </h2>

                    <ol className="news-list">
                        <li className="news-list-item">
                            <time className="news-list-head">2020.6.1</time>
                            <span className="news-list-body">Web予約サービス機能をリリース</span>
                        </li>
                    </ol>
                </div>
            </section>


            <Footer/>
        </div>
    );
};
