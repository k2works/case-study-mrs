import {BasePage} from "../basePage";

export class MeetingRoomsPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/meeting_rooms`;
    }

    visit() {
        cy.visit(this._url);
    }
}
