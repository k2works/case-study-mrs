import {BasePage} from "../basePage";

export class LoginPage extends BasePage {
  constructor() {
    super();
    this._url = `${this._url}/login`;
  }

  visit() {
    cy.visit(this._url);
  }
}
