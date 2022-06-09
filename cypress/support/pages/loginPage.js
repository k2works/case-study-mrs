import {BasePage} from "../basePage";

export class LoginPage extends BasePage {
  constructor() {
    super();
    this._url = `${this._url}/loginForm`;
  }

  visit() {
    cy.visit(this._url);
  }
}
