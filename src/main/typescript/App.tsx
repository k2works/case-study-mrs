import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "./static/css/style.scss";
import {Main} from "./components/home/MainComponent";
import {Content} from "./components/home/ContentComponent";
import {Login} from "./components/home/LoginComponent";

const App: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/content" element={<Content/>}/>
                <Route path="/login" element={<Login/>}/>
            </Routes>
        </div>
    );
};

export default App;
