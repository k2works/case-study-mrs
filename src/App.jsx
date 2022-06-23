import * as React from "react";

import {SubComponent} from "./component/sub-component";

class App extends React.Component {
    render() {
        return (
            <div>
                <h1>Hello React!</h1>
                <SubComponent name="My Counter for Babel"/>
            </div>
        );
    }
}

export default App;
