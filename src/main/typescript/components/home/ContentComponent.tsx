import React from "react";
import "../../static/css/style.scss";
import Service from "../../static/img/service.jpg";
import {Header} from "./HeaderComponent";
import {Footer} from "./FooterComponent";

export const Content: React.FC<{}> = () => {
    const openMenu = () => {
        document.querySelector("html")?.classList.toggle("open");
    };

    return (
        <div>
            <Header/>

            <article className="entry">
                <figure className="entry-img">
                    <img alt="" height="470" src={Service} width="1600"/>
                </figure>

                <div className="w-container">
                    <h1 className="heading-decoration">サービス案内</h1>
                    <p>Services</p>

                    <div className="entry-container">
                        <p>ゆく川の流れは絶えずして、しかも、もとの水にあらず。淀みに浮ぶうたかたは、かつ消えかつ結びて、久しくとどまりたるためしなし。</p>
                        <p>世の中にある人とすみかと、またかくのごとし。たましきの都のうちに、棟を並べ、蔓を争へる、高き、卑しき人のすまひは、世々を鰹て尽きせぬものなれど、これをまことかと尋ぬれば、昔ありし家はまれなり。或は、去年焼けて今年作れり。或は、大家滅びて小家となる。</p>
                        <p>住む人も、これに同じ。所もかはらず、人も多かれど、いにしへ見し人は、二三十人が中に、わづかに一人二人なり。朝に死し、夕に生るるならひ、ただ水の泡にぞ似たりける。</p>
                        <p>知らず、生れ死ぬる人、いづかたより宸りて、いづかたへか去る。また知らず、仮の宿り、誰がためにか心を悩まし、何によりてか、目を喜ばしむる。その主とすみかと、無鴛を争ふさま、いはば朝顔の露に異ならず。或は、露落ちて花残れり。残るといへども、朝日に枯れぬ。或は、花はしぼみて露なほ消えず。消えずといへども、夕べを待つことなし。</p>
                    </div>
                </div>
            </article>

            <Footer/>
        </div>
    );
};
