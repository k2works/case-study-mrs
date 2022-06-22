import {BasePage} from "../basePage";

export class ContactRegistPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/contacts/regist`;
    }

    visit() {
        cy.visit(this._url);
    }
}
