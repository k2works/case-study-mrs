import {BasePage} from "../basePage";

export class ContactGustRegistPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/contacts/gust/regist`;
    }

    visit() {
        cy.visit(this._url);
    }
}
