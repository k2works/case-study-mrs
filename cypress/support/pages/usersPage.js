import {BasePage} from "../basePage";

export class UsersPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/users`;
    }

    visit() {
        cy.visit(this._url);
    }
}
