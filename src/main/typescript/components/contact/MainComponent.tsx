import React, {useEffect, useRef, useState} from "react";
import {AppMenu} from "../share/AppMenuComponent";
import {AppHeader} from "../share/AppHeaderComponent";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {useAppSelector} from "../../app/hook";
import {clearMessage, selectMessage, setMessage} from "../../features/message/messageSlice";
import {contactList, contactState} from "../../features/contact/contactSlice";
import {useInterval} from "../auth/LoginComponent";
import {PageNation} from "../share/PageNationComponent";

export const Main: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const contact = useAppSelector(contactState);
    const updateRef = useRef<HTMLDivElement>(null);
    const [contactId, setContactId] = useState<string>("");
    const [contactDetails, setContactDetails] = useState<string>("");
    const [load, setLoad] = useState(false);
    const [count, setCount] = useState(".");

    useEffect(() => {
        dispatch(clearMessage());
        list().then(r => (setSuccessful(true)));
    }, []);

    useInterval(() => {
        setCount(count + ".");
        if (count.length > 10) {
            setCount(".");
        }
    }, 500);

    const list = async () => {
        setSuccessful(false);
        const resultAction = await dispatch(contactList(1));
        if (contactList.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            setSuccessful(true);
        } else {
            if (resultAction.payload) {
                dispatch(setMessage(resultAction.payload.message));
            } else {
                dispatch(setMessage(resultAction.error.message));
            }
        }
    }

    const handleUpdateDialog = (e: any) => {
        e.preventDefault();
        const id = e.target.dataset.contactId;
        const details = e.target.dataset.contactDetails;
        setContactId(id);
        setContactDetails(details);

        if (updateRef.current) {
            updateRef.current.style.left = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.top = ((window.innerWidth - 500) / 2) + 'px';
            updateRef.current.style.display = 'block';
        }
    }

    const handleClose = (e: any) => {
        e.preventDefault();
        if (updateRef.current) {
            updateRef.current.style.display = 'none';
        }
    }

    const formatContactId = (id: string) => {
        return id.slice(0, 8) + '...';
    }

    const formatDetails = (details: string) => {
        return details.slice(0, 20) + '...';
    }

    const handlePageNation = async (e: any) => {
        setLoad(true);
        const page = e.target.dataset["page"];
        await dispatch(contactList({page: page}));
        setLoad(false);
    }

    return (
        <div>
            <AppHeader/>
            <div className="app">
                <div className="app-container w-container">
                    <AppMenu/>

                    <div className="app-decoration">
                        {load ? <div className="loading">{count}</div> : null}
                        {!load && contact.pageInfo.pages > 10 ? PageNation({
                            handler: handlePageNation,
                            pageInfo: contact.pageInfo
                        }) : <></>}
                    </div>

                    <div className="app-decoration">
                        <table className="app-table">
                            <tbody>
                            <tr>
                                <th>問い合わせ番号</th>
                                <th>問い合わせ内容</th>
                                <th>利用者区分</th>
                            </tr>
                            {
                                contact.contacts.list.map((item: any) => (
                                    <tr>
                                        <td>
                                            <span>{formatContactId(item.contactId.value)}</span>
                                        </td>
                                        <td>
                                            <span>{formatDetails(item.details)}</span>
                                        </td>
                                        <td>
                                            <span>{item.user.roleName}</span>
                                        </td>
                                        <td>
                                            <button
                                                className="app-btn"
                                                data-contact-id={item.contactId.value}
                                                data-contact-details={item.details}
                                                onClick={handleUpdateDialog}>
                                                詳細
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>
                    </div>

                    <div id="updateDialog" ref={updateRef}>
                        <div>
                            <form className="app-form">
                                <label>問い合わせ番号</label>
                                <input id="update_id" name="contactId" readOnly={true} type="text" value={contactId}/>
                                <ul>

                                </ul>
                                <label>問い合わせ内容</label>
                                <textarea id="update_details" name="details" readOnly={true}
                                          value={contactDetails}></textarea>
                                <ul>

                                </ul>
                                <button className="app-btn app-btn-accent" onClick={handleClose}>閉じる</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
