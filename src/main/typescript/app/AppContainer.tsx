import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "../static/css/style.scss";
import {Main} from "../components/home/MainComponent";
import {Content} from "../components/home/ContentComponent";
import {Login} from "../components/home/LoginComponent";
import {Main as Rooms} from "../components/reservation/MainComponent";
import {ReserveForm} from "../components/reservation/ReserveFormComponent";
import {Contact} from "../components/home/ContactComponent";
import {ContactRegist} from "../components/reservation/ContactComponent";
import {Main as MeetingRooms} from "../components/meetingRoom/MainComponent";
import {Main as ReservableRooms} from "../components/reservableRoom/MainComponent";
import {Main as User} from "../components/user/MainComponent";

const AppContainer: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/contents" element={<Content/>}/>
                <Route path="/contacts" element={<Contact/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/rooms" element={<Rooms/>}/>
                <Route path="/reservations" element={<ReserveForm/>}/>
                <Route path="/contact_regist" element={<ContactRegist/>}/>
                <Route path="/meeting_rooms" element={<MeetingRooms/>}/>
                <Route path="/reservable_meeting_rooms" element={<ReservableRooms/>}/>
                <Route path="/users" element={<User/>}/>
            </Routes>
        </div>
    );
};

export default AppContainer;
