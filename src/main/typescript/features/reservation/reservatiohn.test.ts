import apiModule from '../../services/reservationService'
import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import {reservationCancel, reservationList, reservationReserve} from "./reservationSlice";

jest.mock('../../services/reservationService')

describe('reservation reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/reservationService')
    })

    describe('list', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { date: Date, roomId: number };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {date: new Date('2021-04-15'), roomId: 1};
            result = {
                "list": [
                    {
                        "reservationId": {
                            "value": 2
                        },
                        "reservedTime": {
                            "startTime": "09:00:00",
                            "endTime": "10:00:00"
                        },
                        "reservableRoom": {
                            "reservableRoomId": {
                                "roomId": {
                                    "value": 1
                                },
                                "reservedDate": {
                                    "value": "2022-07-05"
                                },
                                "roomNumber": null
                            },
                            "meetingRoom": {
                                "roomId": {
                                    "value": 1
                                },
                                "roomName": "新木場",
                                "roomNumber": null,
                                "reservableRooms": null,
                                "handler": {
                                    "type": "mrs.domain.model.facility.room.MeetingRoom",
                                    "lazyLoader": {
                                        "loaderMap": {
                                            "RESERVABLEROOMS": {
                                                "configurationFactory": null,
                                                "property": "reservableRooms",
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
                            },
                            "reservations": null
                        },
                        "user": {
                            "userId": {
                                "value": "U000001"
                            },
                            "password": {
                                "value": "$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG"
                            },
                            "name": {
                                "firstName": "Aaa",
                                "lastName": "Aaa"
                            },
                            "roleName": "一般",
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
                ]
            }
            action = reservationList(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.list).toHaveBeenCalledWith(arg);
        })

        test('予約一覧を取得する', async () => {
            const data = await action(dispatch, getState, undefined);
            expect(data.payload).toStrictEqual(result)
        })
    })

    describe('reserve', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { date: Date, start: string, end: string, roomId: number, userId: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {date: new Date('2021-04-15'), start: '10:00', end: '11:00', roomId: 1, userId: 'aaaa'};
            result = {}

            action = reservationReserve(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.reserve).toHaveBeenCalledWith(arg);
        })
    })


    describe('cancel', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { date: Date, roomId: number, reservationId: number, userId: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {date: new Date('2021-04-15'), roomId: 1, reservationId: 1, userId: 'aaaa'};
            result = {}

            action = reservationCancel(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.cancel).toHaveBeenCalledWith(arg);
        })
    })
})
