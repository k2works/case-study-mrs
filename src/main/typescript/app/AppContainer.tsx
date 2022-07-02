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
            </Routes>
        </div>
    );
};

export default AppContainer;
