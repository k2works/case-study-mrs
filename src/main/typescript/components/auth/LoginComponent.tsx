import React, {useState} from "react";
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

export const Login: React.FC<{}> = () => {
    const navigate = useNavigate();

    const [userId, setUserId] = useState("U000001");
    const [password, setPassword] = useState("pAssw0rd");
    const [successful, setSuccessful] = useState(false);

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

    const handleLogin = async (e: any) => {
        setSuccessful(false);
        const resultAction = await dispatch(authLogin({id: userId, password}));
        if (authLogin.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
            navigate("/rooms");
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
                        <form className="login-form" onSubmit={handleSubmit(handleLogin)}>
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
