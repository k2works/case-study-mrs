import React, {useState} from "react";
import {Header} from "./HeaderComponent";
import {useNavigate} from "react-router-dom";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import type {AppDispatch, RootState} from '../../app/store';
import {authLogin} from "../../features/auth/authSlice";
import {selectMessage, setMessage} from "../../features/message/messageSlice";

export const Login: React.FC<{}> = () => {
    const navigate = useNavigate();

    const [userId, setUserId] = useState("U000009");
    const [password, setPassword] = useState("pAssw0rd");
    const [successful, setSuccessful] = useState(false);

    const dispatch = useDispatch<AppDispatch>();
    const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
    const {message} = useSelector(selectMessage);

    const onChangeUserId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUserId(e.target.value);
    }

    const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleLogin = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);
        const resultAction = await dispatch(authLogin({id: userId, password}));
        if (authLogin.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
            navigate("/reservations");
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    };

    return (
        <div>
            <Header/>

            <section className="login">
                <div className="login-container w-container">
                    <div className="login-form">
                        <form className="login-form" onSubmit={handleLogin}>
                            <label htmlFor="username">利用者番号:</label>
                            <input id="userid" name="userid" type="text" value={userId} onChange={onChangeUserId}/>
                            <label htmlFor="password">パスワード:</label>
                            <input id="password" name="password" type="password" value={password}
                                   onChange={onChangePassword}/>
                            <button className="btn" type="submit" onClick={handleLogin}>ログイン</button>
                        </form>
                    </div>
                </div>
            </section>

        </div>
    )
}
