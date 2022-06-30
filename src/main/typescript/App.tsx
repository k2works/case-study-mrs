import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "./static/css/style.scss";
import {Main} from "./components/home/MainComponent";
import {Content} from "./components/home/ContentComponent";

const App: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/content" element={<Content/>}/>
            </Routes>
        </div>
    );
};

export default App;
