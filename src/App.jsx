import * as React from "react";
import {Route, Routes} from "react-router-dom";
import "./style.scss";
import {MainComponent} from "./component/main-component";

class App extends React.Component {
    render() {
        return (
            <div>
                <Routes>
                    <Route path="/" element={<MainComponent/>}/>
                </Routes>
            </div>
        );
    }
}

export default App;
