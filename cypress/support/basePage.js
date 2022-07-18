export class BasePage {
    constructor(url = "http://localhost:8081") {
        this._url = `${url}`;
    }

    login() {
        cy.visit(this._url);
        cy.get('#login').click()
        cy.wait(1000)
    }

    loginAdmin() {
        cy.visit(this._url);
        cy.get('#userId').clear().type('U000003')
        cy.get('#login').click()
        cy.wait(1000)
    }

    loginBy(id, password) {
        cy.visit(this._url);
        cy.get('#userId').clear().type(id)
        cy.get('#password').clear().type(password)
        cy.get('#login').click()
    }
}
