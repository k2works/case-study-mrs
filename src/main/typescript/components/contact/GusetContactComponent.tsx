import React from "react";
import "../../static/css/style.scss";
import {Header} from "../home/HeaderComponent";

export const GustContact: React.FC<{}> = () => {
    return (
        <div>
            <Header/>

            <section className="app">
                <div className="app-container">
                    <div className="app-decoration">
                        <div className="message">
                        </div>
                    </div>

                    <div className="app-decoration">
                        <form className="app-form" method="post" action="/contacts/gust">
                            <input type="hidden"
                                   name="_csrf"
                                   value="cd3137a5-cf48-4fc7-9026-d83855a7df96"/>

                            <label>内容:</label>
                            <textarea id="regist_details" name="details"></textarea>
                            <ul>

                            </ul>

                            <button className="app-btn" name="regist" type="submit">送信</button>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    )
}

