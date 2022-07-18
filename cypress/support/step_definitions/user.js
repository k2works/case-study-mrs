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
    cy.get(`#${value} > :nth-child(1)`).should('contain', value);
});

Given(/^利用者番号 "([^"]*)" を新規登録する$/, (value) => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type(value);
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});

Given(/^存在する利用者を新規登録する$/, function () {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type('U000001');
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Then(/^利用者登録画面に "([^"]*)" がエラー表示される$/, (value) => {
    cy.get('.error').should('contain', value);
});

Given(/^利用者番号 "([^"]*)" の利用者情報を更新する$/, (value) => {
    cy.get(`#${value} > :nth-child(5) > .app-btn`).click();
    cy.get('#update_first_name').clear().type('更新');
    cy.get('#update_last_name').clear().type('更新');
    cy.get('#update_password').click().type('A234567z');
    cy.get('#update_role').select('管理者');
    cy.get('[name="update"]').click();
});

Given(/^利用者番号 "([^"]*)" を削除する$/, (value) => {
    cy.get(`#${value} > :nth-child(6) > .app-btn`).click();
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

Given(/^利用者番号を空白で新規登録する$/, () => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear();
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Given(/^名前を空白で新規登録する$/, () => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type('U000001');
    cy.get('#regist_first_name').clear();
    cy.get('#regist_last_name').clear();
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Given(/^パスワードを空白で新規登録する$/, () => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type('U000020');
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click();
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Given(/^利用者番号を "([^"]*)" で新規登録する$/, (value) => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type(value);
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click().type('a234567Z');
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});

Given(/^パスワードを "([^"]*)" で新規登録する$/, (value) => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').clear().type('U000001');
    cy.get('#regist_first_name').clear().type('姓');
    cy.get('#regist_last_name').clear().type('名');
    cy.get('#regist_password').click().type(value);
    cy.get('#regist_role').select('一般');
    cy.get('[name="regist"]').click();
});
