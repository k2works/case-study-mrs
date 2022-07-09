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

Given(/^"([^"]*)" を "([^"]*)" に更新する$/, (value1, value2) => {
    cy.get(':nth-child(9) > :nth-child(3) > button.app-btn').click();
    cy.get('#update_roomName').clear().type(value2);
    cy.get('[name="update"]').click();
});

Given(/^"([^"]*)" を削除する$/, function () {
    cy.get(':nth-child(9) > :nth-child(4) > .app-btn').click();
});

Then(/^会議室一覧に "([^"]*)" が表示される$/, (value) => {
    cy.get('.success').should('contain', value);
});

Given(/^会議室番号:"([^"]*)" 会議室名: "([^"]*)" で登録する$/, (roomId, roomName) => {
    cy.get('#regist_button').click();
    cy.get('#regist_id').click().type(roomId);
    cy.get('#regist_roomName').clear().type(roomName);
    cy.get('[name="regist"]').click();
});

Then(/^会議室一覧に "([^"]*)" がエラー表示される$/, (value) => {
    cy.get('.error').should('contain', value);
});
