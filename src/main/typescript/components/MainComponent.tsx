import * as React from "react";
import "../static/css/style.scss";
import Logo from "../static/img/logo.svg";
import Tool from "../static/img/tool.jpg";
import Helpful from "../static/img/helpful.jpg";
import News01 from "../static/img/news01.jpg";

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
                    </div>
                    <figure className="img">
                        <img src={Tool} alt="" width={1600} height={1260}/>
                    </figure>
                </div>
            </section>

            <section className="imgtext">
                <div className="imgtext-container reverse w-container">
                    <div className="text">
                        <h2 className="heading-decoration">効率化の手助け</h2>
                        <p>Helpful</p>
                        <p>
                            さまざまな作業を効率よく進めるためには、目的に応じて最適な道具を使うことが大切です。そんな道具たちが揃っています。
                        </p>
                    </div>
                    <figure className="img">
                        <img src={Helpful} alt="" width={1600} height={1260}/>
                    </figure>
                </div>
            </section>

            <section className="posts">
                <div className="w-container">
                    <h2 className="heading">
                        News Release <span>最新情報</span>
                    </h2>

                    <div className="posts-container">
                        <article className="post">
                            <a href="#">
                                <figure>
                                    <img src={News01} alt="" width={1000} height={750}/>
                                </figure>
                                <h3>スパンコール</h3>
                                <p>
                                    キラキラと光を反射する装飾素材です。いつもの道具にアクセントを付けます。
                                </p>
                            </a>
                        </article>
                    </div>
                </div>
            </section>
        </div>
    );
};
