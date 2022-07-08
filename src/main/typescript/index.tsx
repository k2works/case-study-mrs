import React from "react";
import {createRoot} from "react-dom/client";
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import AppContainer from "./app/AppContainer";
import {store} from "./app/store";

const container = document.getElementById("app");
if (container) {
    const root = createRoot(container);
    root.render(
        <Provider store={store}>
            <BrowserRouter>
                <AppContainer/>
            </BrowserRouter>
        </Provider>
    );
}

if (process.env.NODE_ENV !== "production") {
    const Dev = require("./Dev.js");
    Dev.setUp();
}
