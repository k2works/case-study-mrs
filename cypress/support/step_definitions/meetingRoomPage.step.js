const {
    Before,
    After,
    Given,
    Then,
} = require("cypress-cucumber-preprocessor/steps");

import {MeetingRoomsPage} from "../pages/meetingRoomsPage";

// this will get called before each scenario
let page;
Before(() => {
    page = new MeetingRoomsPage();
    cy.wait(0);
});

Given(/^"([^"]*)" を登録する$/, (value) => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').click().type(8);
    cy.get('#regist_roomName').clear().type(value);
    cy.get('[name="regist"]').click();
});

Then(/^会議室一覧に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});
