import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import {meetingRoomCreate, meetingRoomDelete, meetingRoomList, meetingRoomUpdate} from "./meetingRoomSlice";
import apiModule from "../../services/meetingRoomService";

jest.mock('../../services/meetingRoomService');

describe('meeting room reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/meetingRoomService')
    })

    describe('list', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: {};
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {page: 1}
            result =
                {
                    "total": 7,
                    "list": [
                        {
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
                        {
                            "roomId": {
                                "value": 2
                            },
                            "roomName": "辰巳",
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
                        {
                            "roomId": {
                                "value": 3
                            },
                            "roomName": "豊洲",
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
                        {
                            "roomId": {
                                "value": 4
                            },
                            "roomName": "月島",
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
                        {
                            "roomId": {
                                "value": 5
                            },
                            "roomName": "新富町",
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
                        {
                            "roomId": {
                                "value": 6
                            },
                            "roomName": "銀座一丁目",
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
                        {
                            "roomId": {
                                "value": 7
                            },
                            "roomName": "有楽町",
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
                        }
                    ],
                    "pageNum": 1,
                    "pageSize": 10,
                    "size": 7,
                    "startRow": 1,
                    "endRow": 7,
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

            action = meetingRoomList(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.list).toHaveBeenCalledWith(1);
        })

        test('会議室一覧を取得する', async () => {
            const data = await action(dispatch, getState, undefined);
            expect(data.payload).toStrictEqual(result)
        })
    })

    describe('create', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { roomId: number, roomName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {roomId: 1, roomName: '月島'}
            result = {}

            action = meetingRoomCreate(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.create).toHaveBeenCalledWith(arg);
        })
    })

    describe('update', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { roomId: number, roomName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {roomId: 1, roomName: '月島'}
            result = {}

            action = meetingRoomUpdate(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.update).toHaveBeenCalledWith(arg);
        })
    })

    describe('delete', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { roomId: number, roomName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {roomId: 1, roomName: '月島'}
            result = {}

            action = meetingRoomDelete(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.delete).toHaveBeenCalledWith(arg);
        })
    })
})
