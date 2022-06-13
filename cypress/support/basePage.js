export class BasePage {
    constructor(url = "http://localhost:8080") {
        this._url = `${url}`;
    }

    login() {
        cy.visit(this._url);
        cy.get('button').click()
    }

    loginAdmin() {
        cy.visit(this._url);
        cy.get('#username').clear().type('U000003')
        cy.get('button').click()
    }

    loginBy(id, password) {
        cy.visit(this._url);
        cy.get('#username').clear().type(id)
        cy.get('#password').clear().type(password)
        cy.get('button').click()
    }
}
