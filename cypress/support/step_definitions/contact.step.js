import {ContactPage} from "../pages/contactPage";

const {
  Before,
  After,
  Given,
  Then,
} = require("cypress-cucumber-preprocessor/steps");

// this will get called before each scenario
let page;
Before(() => {
  page = new ContactPage();
  cy.wait(0);
});

Then(/^問い合わせ一覧画面に "([^"]*)" が表示される$/, (value) => {
  cy.get(':nth-child(2) > :nth-child(2) > span').should('contain', value);
});
