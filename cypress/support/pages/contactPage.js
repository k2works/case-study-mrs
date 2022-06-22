import {BasePage} from "../basePage";

export class ContactPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/contacts`;
    }

    visit() {
        cy.visit(this._url);
    }
}
