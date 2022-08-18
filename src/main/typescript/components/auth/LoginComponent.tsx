import React, {useEffect, useRef, useState} from "react";
import {Header} from "../home/HeaderComponent";
import {useNavigate} from "react-router-dom";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import type {AppDispatch, RootState} from '../../app/store';
import {authLogin} from "../../features/auth/authSlice";
import {selectMessage, setMessage} from "../../features/message/messageSlice";
import {useForm} from "react-hook-form";

type FormData = {
    userId: String;
    password: String;
}

export const useInterval = (callback: Function, delay?: number | null) => {
    const savedCallback = useRef<Function>(() => {
    });
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);
    useEffect(() => {
        if (delay !== null) {
            const interval = setInterval(() => savedCallback.current(), delay || 0);
            return () => clearInterval(interval);
        }
        return undefined;
    }, [delay]);
};

export const Login: React.FC<{}> = () => {
    const navigate = useNavigate();

    const [userId, setUserId] = useState("U000001");
    const [password, setPassword] = useState("pAssw0rd");
    const [successful, setSuccessful] = useState(false);
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState(".");

    const {register, handleSubmit, formState: {errors}} = useForm<FormData>();

    const dispatch = useDispatch<AppDispatch>();
    const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
    const {message} = useAppSelector(selectMessage);

    const onChangeUserId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUserId(e.target.value);
    }

    const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    useInterval(() => {
        setCount(count + ".");
        if (count.length > 10) {
            setCount(".");
        }
    }, 500);

    const handleLogin = async (e: any) => {
        setSuccessful(false);
        setLoad(true);
        const resultAction = await dispatch(authLogin({id: userId, password}));
        if (authLogin.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
            navigate("/rooms");
            setLoad(false);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setLoad(false);
            setSuccessful(false);
        }
    };

    return (
        <div>
            <Header/>

            <section className="login">
                <div className="login-container w-container">
                    <div className="login-form">
                        <form className="login-form" onSubmit={handleSubmit(handleLogin)}>
                            {load ? <div className="loading">{count}</div> : null}
                            {message && <div className="error">{message}</div>}
                            <label htmlFor="username">利用者番号:</label>
                            <input {...register("userId", {required: true})} id="userId" value={userId}
                                   onChange={onChangeUserId}/>
                            {errors.userId && <p className="error">利用者番号を入力してください</p>}
                            <label htmlFor="password">パスワード:</label>
                            <input {...register("password", {required: true})} id="password" type="password"
                                   value={password}
                                   onChange={onChangePassword}/>
                            {errors.password && <p className="error">パスワードを入力してください</p>}
                            <button id="login" className="btn" type="submit">ログイン</button>
                        </form>
                    </div>
                </div>
            </section>

        </div>
    )
}
