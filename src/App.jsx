import * as React from "react";
import "./style.scss";
import Image from "./thumb.jpg";

import {SubComponent} from "./component/sub-component";

class App extends React.Component {
    render() {
        return (
            <div>
                <h1>Hello React!</h1>
                <SubComponent name="My Counter for Babel"/>

                <img src={Image} alt="image"/>

                <div class="my-grid">
                    <header class="my-grid-item">ヘッダー</header>
                    <aside class="my-grid-item">サイドバー</aside>
                    <main class="my-grid-item">メインコンテンツ</main>
                    <footer class="my-grid-item">フッター</footer>
                </div>
            </div>
        );
    }
}

export default App;
