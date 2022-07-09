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

Given(/^未入力でログインする$/, function () {
  page = new LoginPage()
  page.visit()
  cy.visit(page._url);
  cy.get('#userId').clear()
  cy.get('#password').clear()
  cy.get('#login').click()
});

Given(/^利用者番号: "([^"]*)" パスワード: "([^"]*)" でログインする$/, (id, password) => {
  page = new LoginPage()
  page.visit()
  page.loginBy(id, password)
});

Then(`{string} がログイン画面に表示される`, (value) => {
  cy.get('.btn').should("contain", value);
});

Then(/^"([^"]*)" と "([^"]*)" がログイン画面に表示される$/, (value1, value2) => {
  cy.get('.login-form > :nth-child(3)').should("contain", value1);
  cy.get('.login-form > :nth-child(6)').should("contain", value2);
});

Then(/^"([^"]*)" がログイン画面にエラー表示される$/, (value) => {
  cy.get('.error').should("contain", value);
});
