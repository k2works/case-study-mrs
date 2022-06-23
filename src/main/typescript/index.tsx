import React from "react";
import {createRoot} from "react-dom/client";
import {BrowserRouter} from "react-router-dom";
import {configureStore} from "@reduxjs/toolkit";
import {Provider} from "react-redux";
import rootReducer from "./reducers";
import App from "./App";

const container = document.getElementById("app");
if (container) {
    const root = createRoot(container);
    const store = configureStore({
        reducer: rootReducer,
    });

    root.render(
        <Provider store={store}>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </Provider>
    );
}

const Dev = require("./Dev.js");
Dev.setUp();
