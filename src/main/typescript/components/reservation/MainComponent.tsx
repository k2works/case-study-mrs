import React from "react";
import "../../static/css/style.scss";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";

export const Main: React.FC<{}> = () => {
    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">

                    </div>

                    <div className="app-decoration">
                        <a href="/rooms/2022-06-29">&lt; 前日</a>
                        <span>2022/6/30の会議室</span>
                        <a href="/rooms/2022-07-01">翌日 &gt;</a>
                    </div>

                    <div className="app-decoration">
                        <ul>
                            <li>
                                <a href="/reservations" id="No1">新木場</a>
                            </li>
                            <li>
                                <a href="/reservations" id="No7">有楽町</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    )
}
