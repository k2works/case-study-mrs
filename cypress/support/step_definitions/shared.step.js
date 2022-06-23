import {ReservableRoomsPage} from "../pages/reservableRoomsPage";
import {LoginPage} from "../pages/loginPage";
import {RoomsPage} from "../pages/roomsPage";
import {UsersPage} from "../pages/usersPage";
import {MeetingRoomsPage} from "../pages/meetingRoomsPage";
import {ContactRegistPage} from "../pages/contactRegistPage";
import {ContactPage} from "../pages/contactPage";
import {ContactGustRegistPage} from "../pages/contactGustRegistPage";

const {
    Before,
    After,
    Given,
    Then,
} = require("cypress-cucumber-preprocessor/steps");

// this will get called before each scenario
let page;
Before(() => {
    cy.wait(0);
});

Given(`{string} ページにアクセスする`, (pageName) => {
    switch (pageName) {
        case "ログイン":
            page = new LoginPage();
            break;
        case "会議室予約一覧画面":
            page = new RoomsPage();
            break;
        case "利用者一覧画面":
            page = new UsersPage();
            break;
        case "会議室一覧画面":
            page = new MeetingRoomsPage();
            break;
        case "予約可能会議室一覧画面":
            page = new ReservableRoomsPage();
            break;
        case "問い合わせ画面":
            page = new ContactRegistPage();
            break;
        case "問い合わせ一覧画面":
            page = new ContactPage();
            break;
        case "ゲスト問い合わせ画面":
            page = new ContactGustRegistPage();
            break;
        default:
            console.log("該当するページが存在しません");
    }
    page.visit();
});

Then(`機能名 {string} が表示される`, (funcName) => {
    cy.get("body > :nth-child(1)").should("contain", funcName);
});

Given(`{string} としてログインしている`, (user) => {
    switch (user) {
        case ("会員"):
            page = new LoginPage()
            page.visit()
            page.login()
            break
        case ("スタッフ"):
            page = new LoginPage()
            page.visit()
            page.loginAdmin()
            break
        default:
            throw new Error('該当するページが存在しません')
    }
});
