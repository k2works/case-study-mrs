import {BasePage} from "../basePage";

export class RoomsPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/rooms`;
    }

    visit() {
        cy.visit(this._url);
    }
}
