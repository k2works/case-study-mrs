import apiModule from "../../services/contactService";
import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import {contactCreate, contactList} from "./contactSlice";

jest.mock('../../services/contactService')

describe('room reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/contactService')
    })

    describe('list', () => {
        let action: AsyncThunkAction<void, Date, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: any;
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {page: 1};
            result =
                {
                    "total": 1,
                    "list": [
                        {
                            "contactId": {
                                "value": "464a1842-014a-4127-a113-01506d3246b1"
                            },
                            "details": "aaaaaaaaaaaaa",
                            "member": {
                                "userId": {
                                    "value": "U999999"
                                },
                                "password": null,
                                "name": null,
                                "roleName": null,
                                "reservations": null,
                                "user": null,
                                "memberId": null
                            },
                            "user": {
                                "userId": {
                                    "value": "U999999"
                                },
                                "password": {
                                    "value": ""
                                },
                                "name": {
                                    "firstName": "",
                                    "lastName": ""
                                },
                                "roleName": "ゲスト",
                                "reservations": null,
                                "handler": {
                                    "type": "mrs.domain.model.auth.user.User",
                                    "lazyLoader": {
                                        "loaderMap": {
                                            "RESERVATIONS": {
                                                "configurationFactory": null,
                                                "property": "reservations",
                                                "mappedStatement": null,
                                                "mappedParameter": null
                                            }
                                        }
                                    },
                                    "aggressive": false,
                                    "lazyLoadTriggerMethods": [
                                        "hashCode",
                                        "equals",
                                        "clone",
                                        "toString"
                                    ],
                                    "objectFactory": {},
                                    "constructorArgTypes": [],
                                    "constructorArgs": []
                                }
                            }
                        }
                    ],
                    "pageNum": 1,
                    "pageSize": 10,
                    "size": 1,
                    "startRow": 1,
                    "endRow": 1,
                    "pages": 1,
                    "prePage": 0,
                    "nextPage": 0,
                    "isFirstPage": true,
                    "isLastPage": true,
                    "hasPreviousPage": false,
                    "hasNextPage": false,
                    "navigatePages": 8,
                    "navigatepageNums": [
                        1
                    ],
                    "navigateFirstPage": 1,
                    "navigateLastPage": 1
                }

            action = contactList(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.list).toHaveBeenCalledWith(1);
        })

        test('予約一覧を取得する', async () => {
            const data = await action(dispatch, getState, undefined);
            expect(data.payload).toStrictEqual(result)
        })
    })

    describe('createByMember', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { contactId: string, details: string, roleName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {contactId: "", details: "aaaaaaaaaaaaaaaa", roleName: "一般"};
            result = {}

            action = contactCreate(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.createByMember).toHaveBeenCalledWith({contactId: arg.contactId, details: arg.details});
        })
    })

    describe('createByGuest', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { contactId: string, details: string, roleName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {contactId: "", details: "aaaaaaaaaaaaaaaa", roleName: "ゲスト"};
            result = {}

            action = contactCreate(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.createByGuest).toHaveBeenCalledWith({contactId: arg.contactId, details: arg.details});
        })
    })
})
