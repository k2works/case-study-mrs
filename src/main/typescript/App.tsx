import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "./static/css/style.scss";
import {Main} from "./components/home/MainComponent";
import {Content} from "./components/home/ContentComponent";
import {Login} from "./components/home/LoginComponent";
import {Main as Rooms} from "./components/room/MainComponent";
import {ReserveForm} from "./components/room/ReserveFormComponent";

const App: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/content" element={<Content/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/rooms" element={<Rooms/>}/>
                <Route path="/reservations" element={<ReserveForm/>}/>
            </Routes>
        </div>
    );
};

export default App;
