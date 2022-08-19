import React, {useEffect, useRef, useState} from "react";
import {AppHeader} from "../share/AppHeaderComponent";
import {AppMenu} from "../share/AppMenuComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {useAppSelector} from "../../app/hook";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {roleNames, userCreate, userDelete, userList, userState, userUpdate} from "../../features/user/userSlice";
import {useForm} from "react-hook-form";
import {useInterval} from "../auth/LoginComponent";
import {PageNation} from "../share/PageNationComponent";

type FormData = {
    registUserId: String;
    registFirstName: String;
    registLastName: String;
    registPassword: String;
    updateFirstName: String;
    updateLastName: String;
    updatePassword: String;
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
    const {setValue, register, handleSubmit, formState: {errors}} = useForm<FormData>();
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState(".");

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
        roleNameList().then(r => (setSuccessful(true)));
    }, []);

    useInterval(() => {
        setCount(count + ".");
        if (count.length > 10) {
            setCount(".");
        }
    }, 500);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(userList(1));
        if (userList.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.data.message));
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
            dispatch(setMessage(resultAction.payload.data.message));
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
        setUserFirstName('');
        setUserLastName('');
        setUserPassword('');
        setUserRoleName('一般');

        if (registRef.current) {
            registRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            registRef.current.style.top = ((window.innerWidth - 750) / 2) + 'px';
            registRef.current.style.display = 'block';
        }
    }

    const handleUpdateDialog = (e: any) => {
        e.preventDefault();

        const userId = e.target.dataset.userId;
        const userFirstName = e.target.dataset.userFirstName;
        const userLastName = e.target.dataset.userLastName;
        const userRoleName = e.target.dataset.userRoleName;

        setUserId(userId);
        setUserPassword('');
        setUserFirstName(userFirstName);
        setUserLastName(userLastName);
        setUserRoleName(userRoleName);

        setValue('updateFirstName', userFirstName, {shouldValidate: true})
        setValue('updateLastName', userLastName, {shouldValidate: true})

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

    //TODO handleSubmitが機能しない
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
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.data.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handleUpdate = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const params = {
            userId: userId,
            password: userPassword,
            firstName: userFirstName,
            lastName: userLastName,
            roleName: userRoleName
        }

        const resultAction = await dispatch(userUpdate(params));
        if (userUpdate.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.data.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handleDelete = async (e: any) => {
        e.preventDefault();
        setSuccessful(false);

        const userId = e.target.dataset.userId;

        const params = {
            userId: userId,
        }

        const resultAction = await dispatch(userDelete(params));
        if (userDelete.fulfilled.match(resultAction)) {
            setSuccessful(true);
            await list();
            dispatch(setMessage(resultAction.payload.data.message));
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
            setSuccessful(false);
        }
    }

    const handlePageNation = async (e: any) => {
        setLoad(true);
        const page = e.target.dataset["page"];
        await dispatch(userList({page: page}));
        setLoad(false);
    }

    return (
        <div>
            <AppHeader/>
            <section className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">
                        {load ? <div className="loading">{count}</div> : null}
                        {!load && user.pageInfo.pages > 10 ? PageNation({
                            handler: handlePageNation,
                            pageInfo: user.pageInfo
                        }) : <></>}
                    </div>

                    <div className="app-decoration">
                        <div className="message">
                            {successful ? (<p className="success">{message}</p>) : (<p className="error">{message}</p>)}
                        </div>
                    </div>

                    <div className="app-decoration">
                        <button className="regist app-btn" id="regist_button" onClick={handleRegistDialog}>新規</button>
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
                                    <tr id={item.userId.value}>
                                        <td>{item.userId.value}</td>
                                        <td>{item.name.firstName}</td>
                                        <td>{item.name.lastName}</td>
                                        <td>{item.roleName}</td>
                                        <td>
                                            <button
                                                className="app-btn"
                                                data-user-id={item.userId.value}
                                                data-user-first-name={item.name.firstName}
                                                data-user-last-name={item.name.lastName}
                                                data-user-role-name={item.roleName}
                                                onClick={handleUpdateDialog}>
                                                編集
                                            </button>
                                        </td>
                                        <td>
                                            <a
                                                className="app-btn app-btn-accent"
                                                data-user-id={item.userId.value}
                                                onClick={handleDelete}>
                                                削除
                                            </a>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>

                        <div id="registDialog" ref={registRef}>
                            <div>
                                <form className="app-form" onSubmit={handleSubmit(handleRegist)}>
                                    <label>利用者番号</label>
                                    <input {...register("registUserId", {required: true})}
                                           id="regist_id" value={userId} onChange={handleUserId}/>
                                    <ul>
                                        {errors.registUserId && <li className="error">利用者番号を入力してください</li>}
                                    </ul>

                                    <label>姓</label>
                                    <input  {...register("registFirstName", {required: true})}
                                            id="regist_first_name" value={userFirstName}
                                            onChange={handleUserFirstName}/>
                                    <ul>
                                        {errors.registFirstName && <li className="error">姓を入力してください</li>}
                                    </ul>

                                    <label>名</label>
                                    <input  {...register("registLastName", {required: true})}
                                            id="regist_last_name" value={userLastName}
                                            onChange={handleUserLastName}/>
                                    <ul>
                                        {errors.registLastName && <li className="error">姓を入力してください</li>}
                                    </ul>

                                    <label>パスワード</label>
                                    <input  {...register("registPassword", {required: true})}
                                            id="regist_password" type="password" value={userPassword}
                                            onChange={handleUserPassword}/>
                                    <ul>
                                        {errors.registPassword && <li className="error">パスワードを入力してください</li>}
                                    </ul>

                                    <label>役割</label>
                                    <select id="regist_role" value={userRoleName}
                                            onChange={handleUserRoleName}>
                                        {
                                            user.roleNames.map((item: any) => (
                                                <option value={item}>{item}</option>
                                            ))
                                        }
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="regist" type="submit" onClick={handleRegist}>登録
                                    </button>
                                    <button className="app-btn app-btn-accent" onClick={handleClose}>キャンセル</button>
                                </form>
                            </div>
                        </div>

                        <div id="updateDialog" ref={updateRef}>
                            <div>
                                <form className="app-form">
                                    <label>利用者番号</label>
                                    <input id="update_user_id" readOnly={true} type="text" value={userId}/>

                                    <label>姓</label>
                                    <input  {...register("updateFirstName", {required: true})}
                                            id="update_first_name"
                                            value={userFirstName}
                                            onChange={handleUserFirstName}/>
                                    <ul>
                                        {errors.updateFirstName && <li className="error">姓を入力してください</li>}
                                    </ul>

                                    <label>名</label>
                                    <input  {...register("updateLastName", {required: true})}
                                            id="update_last_name"
                                            value={userLastName}
                                            onChange={handleUserLastName}/>
                                    <ul>
                                        {errors.updateLastName && <li className="error">名を入力してください</li>}
                                    </ul>

                                    <label>パスワード</label>
                                    <input id="update_password" name="password" type="password" value={userPassword}
                                           onChange={handleUserPassword}/>
                                    <ul>
                                        <li>未入力の場合はパスワード未更新</li>
                                    </ul>

                                    <label>役割</label>
                                    <select id="update_role" value={userRoleName}
                                            onChange={handleUserRoleName}>
                                        {
                                            user.roleNames.map((item: any) => (
                                                <option value={item}>{item}</option>
                                            ))
                                        }
                                    </select>
                                    <ul>

                                    </ul>
                                    <button className="app-btn" name="update" onClick={handleUpdate}>登録</button>
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
