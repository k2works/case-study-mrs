const {
    Before,
    After,
    Given,
    Then,
} = require("cypress-cucumber-preprocessor/steps");

import {ReservableRoomsPage} from "../pages/reservableRoomsPage";

// this will get called before each scenario
let page;
Before(() => {
    page = new ReservableRoomsPage();
    cy.wait(0);
});

Given(/^"([^"]*)" を "([^"]*)" 予約可能登録する$/, (room, date) => {
    cy.get('#regist_button').click();
    cy.get('#regist_reservedDate').clear().type(date);
    cy.get('#regist_id').select(room);
    cy.get('[name="regist"]').click();
});

Then(/^予約可能会議室一覧に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});

Given(/^予約可能会議室を削除する$/, function () {
    cy.get(':nth-child(2) > :nth-child(4) > .app-btn').click();
});
