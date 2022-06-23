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
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});

Given(/^存在する利用者を新規登録する$/, function () {
    cy.get('#update_button').click();
    cy.get('#regist_id').clear().type('U000004');
    cy.get('#regist_firstName').clear().type('姓');
    cy.get('#regist_lastName').clear().type('名');
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" がエラー表示される$/, (value) => {
    cy.get('.error').should('contain', value);
});

Given(/^利用者番号 "([^"]*)" の利用者情報を更新する$/, (value) => {
    cy.get(':nth-child(7) > :nth-child(5) > button.app-btn').click();
    cy.get('#update_firstName').clear().type('更新');
    cy.get('#update_lastName').clear().type('更新');
    cy.get('#update_password').click().type('A234567z');
    cy.get('#update_role').select('管理者');
    cy.get('[name="update"]').click();
});

Given(/^利用者番号 "([^"]*)" を削除する$/, (value) => {
    cy.get(':nth-child(7) > :nth-child(6) > .app-btn').click();
});

Given(/^新規登録した利用者番号 "([^"]*)" で認証する$/, (value) => {
    page = new LoginPage()
    page.visit()
    page.loginBy(value, 'a234567Z')
});

Then(/^新規登録利用者で認証される$/, () => {
    cy.get('form > a').should('contain', 'ログアウト')
});

Given(/^更新した利用者番号 "([^"]*)" で認証する$/, (value) => {
    page = new LoginPage()
    page.visit()
    page.loginBy(value, 'A234567z')
});

Then(/^更新した利用者で認証される$/, () => {
    cy.get('form > a').should('contain', 'ログアウト')
});
