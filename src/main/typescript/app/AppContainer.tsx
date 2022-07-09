import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "../static/css/style.scss";
import {Main} from "../components/home/MainComponent";
import {Content} from "../components/home/ContentComponent";
import {Login} from "../components/auth/LoginComponent";
import {Main as Rooms} from "../components/reservation/MainComponent";
import {ReserveForm} from "../components/reservation/ReserveFormComponent";
import {Main as MeetingRooms} from "../components/meetingRoom/MainComponent";
import {Main as ReservableRooms} from "../components/reservableRoom/MainComponent";
import {Main as User} from "../components/user/MainComponent";
import {Main as ContactList} from "../components/contact/MainComponent";
import {GustContact} from "../components/contact/GusetContactComponent";
import {MemberContact} from "../components/contact/MemberContactComponent";

const AppContainer: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/contents" element={<Content/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/rooms" element={<Rooms/>}/>
                <Route path="/reservations" element={<ReserveForm/>}/>
                <Route path="/meeting_rooms" element={<MeetingRooms/>}/>
                <Route path="/reservable_meeting_rooms" element={<ReservableRooms/>}/>
                <Route path="/users" element={<User/>}/>
                <Route path="/contacts" element={<ContactList/>}/>
                <Route path="/contacts_gust_regist" element={<GustContact/>}/>
                <Route path="/contacts_member_regist" element={<MemberContact/>}/>
            </Routes>
        </div>
    );
};

export default AppContainer;
