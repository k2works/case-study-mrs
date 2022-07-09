import {BasePage} from "../basePage";

export class ReservableRoomsPage extends BasePage {
    constructor() {
        super();
        this._url = `${this._url}/reservable_meeting_rooms`;
    }

    visit() {
        cy.visit(this._url);
    }
}
