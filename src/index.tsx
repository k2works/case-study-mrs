import React from "react";
import {createRoot} from "react-dom/client";
import {BrowserRouter} from "react-router-dom";
import App from "./App";

const container = document.getElementById("app");
if (container) {
    const root = createRoot(container);
    root.render(
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    );
}

const Dev = require("./Dev.js");
Dev.setUp();
