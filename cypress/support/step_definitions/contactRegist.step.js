import {ContactRegistPage} from "../pages/contactRegistPage";

const {
  Before,
  After,
  Given,
  Then,
} = require("cypress-cucumber-preprocessor/steps");

// this will get called before each scenario
let page;
Before(() => {
  page = new ContactRegistPage();
  cy.wait(0);
});

Given(/^問い合わせ画面で "([^"]*)" を入力する$/, (value) => {
  cy.get('#regist_details').clear().type(value);
  cy.get('.app-btn').click();
});

Then(/^問い合わせが送信される$/, () => {
  cy.get('.success').should('contain', '問い合わせを送信しました');
});
