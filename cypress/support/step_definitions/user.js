const {
    Before,
    After,
    Given,
    Then,
} = require("cypress-cucumber-preprocessor/steps");

import {LoginPage} from "../pages/loginPage";

// this will get called before each scenario
let page;
Before(() => {
    page = new LoginPage();
    cy.wait(0);
});

Then(/^利用者登録画面に利用者番号 "([^"]*)" が表示される$/, (value) => {
    cy.get(':nth-child(2) > :nth-child(1) > span').should('contain', value);
});

Given(/^利用者番号 "([^"]*)" を新規登録する$/, (value) => {
    cy.get('#update_button').click();
    cy.get('#regist_id').clear().type(value);
    cy.get('#regist_firstName').clear().type('姓');
    cy.get('#regist_lastName').clear().type('名');
    cy.get('#regist_password').click().type('パスワード');
    cy.get('#regist_role').select('USER');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});

Given(/^存在する利用者を新規登録する$/, function () {
    cy.get('#update_button').click();
    cy.get('#regist_id').clear().type('U000002');
    cy.get('#regist_firstName').clear().type('姓');
    cy.get('#regist_lastName').clear().type('名');
    cy.get('#regist_password').click().type('パスワード');
    cy.get('#regist_role').select('USER');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" がエラー表示される$/, (value) => {
    cy.get('.error').should('contain', value);
});

Given(/^利用者番号 "([^"]*)" を更新する$/, (value) => {
    cy.get(':nth-child(6) > :nth-child(5) > button.btn').click();
    cy.get('#update_firstName').clear().type('更新');
    cy.get('#update_lastName').clear().type('更新');
    cy.get('#update_password').click().type('更新');
    cy.get('#update_role').select('ADMIN');
    cy.get('[name="update"]').click();
});

Given(/^利用者番号 "([^"]*)" を削除する$/, (value) => {
    cy.get(':nth-child(6) > :nth-child(5) > .btn-accent').click();
});
