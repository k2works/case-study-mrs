import {BasePage} from "../basePage";

export class ContactGustRegistPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/contacts_gust_regist`;
    }

    visit() {
        cy.visit(this._url);
    }
}
