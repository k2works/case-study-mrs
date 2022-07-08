import React, {useState} from "react";
import "../../static/css/style.scss";
import {Header} from "../home/HeaderComponent";
import {useAppSelector} from "../../app/hook";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../app/store";
import {selectMessage, setMessage} from "../../features/message/messageSlice";
import {useForm} from "react-hook-form";
import {contactCreate} from "../../features/contact/contactSlice";

type FormData = {
    details: string
}

export const GustContact: React.FC<{}> = () => {
    const dispatch = useDispatch<AppDispatch>();
    const [successful, setSuccessful] = useState(false);
    const {message} = useAppSelector(selectMessage);
    const {register, handleSubmit, formState: {errors}} = useForm<FormData>();
    const [details, setDetails] = useState("");

    const handleDetails = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        setDetails(e.target.value);
    }

    const handleRegist = async (e: any) => {
        setSuccessful(false);

        const params = {
            contactId: "",
            details: details,
            roleName: "",
        }

        const resultAction = await dispatch(contactCreate(params));
        if (contactCreate.fulfilled.match(resultAction)) {
            dispatch(setMessage(resultAction.payload.message));
            dispatch(setMessage("問い合わせを送信しました"));
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

    return (
        <div>
            <Header/>

            <section className="app">
                <div className="app-container">
                    <div className="app-decoration">
                        <div className="message">
                            {successful ? (<p className="success">{message}</p>) : (<p className="error">{message}</p>)}
                        </div>
                    </div>

                    <div className="app-decoration">
                        <form className="app-form" onSubmit={
                            handleSubmit(async (date) => {
                                    await handleRegist(date);
                                }
                            )}>
                            <label>内容:</label>
                            <textarea {...register("details", {required: true})} id="regist_details" name="details"
                                      value={details}
                                      onChange={handleDetails}></textarea>
                            <ul>
                                {errors.details && <p className="error">内容を入力してください</p>}
                            </ul>

                            <button className="app-btn" type="submit">送信</button>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    )
}

