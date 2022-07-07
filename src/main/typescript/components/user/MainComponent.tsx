import React, {useEffect, useRef, useState} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {useAppSelector} from "../../app/hook";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {roleNames, userCreate, userList, userState} from "../../features/user/userSlice";
import {useForm} from "react-hook-form";

type FormData = {
    userId: String;
    firstName: String;
    lastName: String;
    password: String;
}

export const Main: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const user = useAppSelector(userState);
    const [userId, setUserId] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [userFirstName, setUserFirstName] = useState('');
    const [userLastName, setUserLastName] = useState('');
    const [userRoleName, setUserRoleName] = useState('');
    const registRef = useRef<HTMLDivElement>(null);
    const updateRef = useRef<HTMLDivElement>(null);
    const {register, handleSubmit, formState: {errors}} = useForm<FormData>();

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
        roleNameList().then(r => (setSuccessful(true)));
    }, []);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(userList(1));
        if (userList.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const roleNameList = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(roleNames(1));
        if (roleNames.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handleRegistDialog = (e: any) => {
        e.preventDefault();

        setUserId('');
        setUserPassword('');
        setUserFirstName('');
        setUserLastName('');
        setUserRoleName('一般');

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.top = ((window.innerWidth - 750) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateDialog = (e: any) => {
        e.preventDefault();

        if (updateRef.current) {
            updateRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.top = ((window.innerWidth - 750) / 2) + 'px';
            updateRef.current.style.display = 'block';
        }
    }

    const handleClose = (e: any) => {
        e.preventDefault();
        if (registRef.current) {
            registRef.current.style.display = 'none';
        }
        if (updateRef.current) {
            updateRef.current.style.display = 'none';
        }
    }

    const handleUserId = (e: any) => {
        setUserId(e.target.value);
    }

    const handleUserPassword = (e: any) => {
        setUserPassword(e.target.value);
    }

    const handleUserFirstName = (e: any) => {
        setUserFirstName(e.target.value);
    }

    const handleUserLastName = (e: any) => {
        setUserLastName(e.target.value);
    }

    const handleUserRoleName = (e: any) => {
        setUserRoleName(e.target.value);
    }

    const handleRegist = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const params = {
            userId: userId,
            password: userPassword,
            firstName: userFirstName,
            lastName: userLastName,
            roleName: userRoleName
        }

        const resultAction = await dispatch(userCreate(params));
        if (userCreate.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
            await list();
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }


    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">

                    </div>

                    <div className="app-decoration">
                        <div className="message">
                            {!successful && (<p className="error">{message}</p>)}
                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="update_button" onClick={handleRegistDialog}>新規</button>
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>利用者番号</th>
                                <th>姓</th>
                                <th>名</th>
                                <th>役割</th>
                            </tr>
                            {
                                user.users.list.map((item: any) => (
                                    <tr>
                                        <td>{item.userId.value}</td>
                                        <td>{item.name.lastName}</td>
                                        <td>{item.name.firstName}</td>
                                        <td>{item.roleName}</td>
                                        <td>
                                            <button className="app-btn" onClick={handleUpdateDialog}>
                                                編集
                                            </button>
                                        </td>
                                        <td>
                                            <a className="app-btn app-btn-accent">削除</a>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form">
                                    <label>利用者番号</label>
                                    <input {...register("userId", {required: true})} id="regist_id" name="userId"
                                           type="text" value={userId} onChange={handleUserId}/>
                                    <ul>
                                        {errors.userId && <li className="error">利用者番号を入力してください</li>}
                                    </ul>

                                    <label>姓</label>
                                    <input  {...register("firstName", {required: true})} id="regist_firstName"
                                            name="firstName" type="text" value={userFirstName}
                                            onChange={handleUserFirstName}/>
                                    <ul>
                                        {errors.firstName && <li className="error">姓を入力してください</li>}
                                    </ul>

                                    <label>名</label>
                                    <input  {...register("lastName", {required: true})} id="regist_lastName"
                                            name="lastName" type="text" value={userLastName}
                                            onChange={handleUserLastName}/>
                                    <ul>
                                        {errors.lastName && <li className="error">姓を入力してください</li>}
                                    </ul>

                                    <label>パスワード</label>
                                    <input  {...register("password", {required: true})} id="regist_password"
                                            name="password" type="password" value={userPassword}
                                            onChange={handleUserPassword}/>
                                    <ul>
                                        {errors.password && <li className="error">パスワードを入力してください</li>}
                                    </ul>

                                    <label>役割</label>
                                    <select id="regist_role" name="roleName" value={userRoleName}
                                            onChange={handleUserRoleName}>
                                        {
                                            user.roleNames.map((item: any) => (
                                                <option value={item}>{item}</option>
                                            ))
                                        }
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" onClick={handleSubmit(handleRegist)}>登録
                                    </button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル</button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form">
                                    <label>利用者番号</label>
                                    <input id="update_id" name="userId" readOnly={true} type="text" value=""/>
                                    <label>姓</label>
                                    <input id="update_firstName" name="firstName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>名</label>
                                    <input id="update_lastName" name="lastName" type="text" value=""/>
                                    <ul>

                                    </ul>
                                    <label>パスワード</label>
                                    <input id="update_password" name="password" type="password" value=""/>
                                    <ul>
                                        <li>未入力の場合はパスワード未更新</li>

                                    </ul>
                                    <label>役割</label>
                                    <select id="update_role" name="roleName">
                                        <option value="一般">一般</option>
                                        <option value="管理者">管理者</option>
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="update">登録</button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}
