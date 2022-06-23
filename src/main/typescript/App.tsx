import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "./static/css/style.scss";
import {MainComponent} from "./components/main-component";

const App: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<MainComponent/>}/>
            </Routes>
        </div>
    );
};

export default App;
