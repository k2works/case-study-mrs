import React from "react";
import {Header} from "./HeaderComponent";

export const Login: React.FC<{}> = () => {
    return (
        <div>
            <Header/>

            <section className="login">
                <div className="login-container w-container">
                    <div className="login-form">
                        <form className="login-form" method="GET" action="/rooms">
                            <label htmlFor="username">利用者番号:</label>
                            <input id="username" name="username" type="text" value="U000001"/>
                            <label htmlFor="password">パスワード:</label>
                            <input id="password" name="password" type="password" value="demo"/>
                            <button className="btn" type="submit">ログイン</button>
                        </form>
                    </div>
                </div>
            </section>

        </div>
    )
}
