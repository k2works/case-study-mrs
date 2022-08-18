import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import apiModule from "../../services/reservableRoomService";
import {reservableRoomCreate, reservableRoomDelete, reservableRoomList} from "./reservableRoomSlice";

jest.mock('../../services/reservableRoomService');

describe('meeting room reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/reservableRoomService');
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
                    "total": 4,
                    "list": [
                        {
                            "reservableRoomId": {
                                "roomId": {
                                    "value": 1
                                },
                                "reservedDate": {
                                    "value": "2022-07-07"
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
                            "reservations": null,
                            "handler": {
                                "type": "mrs.domain.model.reservation.room.ReservableRoom",
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
                        },
                        {
                            "reservableRoomId": {
                                "roomId": {
                                    "value": 7
                                },
                                "reservedDate": {
                                    "value": "2022-07-07"
                                },
                                "roomNumber": null
                            },
                            "meetingRoom": {
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
                            },
                            "reservations": null,
                            "handler": {
                                "type": "mrs.domain.model.reservation.room.ReservableRoom",
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
                        },
                        {
                            "reservableRoomId": {
                                "roomId": {
                                    "value": 1
                                },
                                "reservedDate": {
                                    "value": "2022-07-08"
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
                            "reservations": null,
                            "handler": {
                                "type": "mrs.domain.model.reservation.room.ReservableRoom",
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
                        },
                        {
                            "reservableRoomId": {
                                "roomId": {
                                    "value": 7
                                },
                                "reservedDate": {
                                    "value": "2022-07-08"
                                },
                                "roomNumber": null
                            },
                            "meetingRoom": {
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
                            },
                            "reservations": null,
                            "handler": {
                                "type": "mrs.domain.model.reservation.room.ReservableRoom",
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
                    ],
                    "pageNum": 1,
                    "pageSize": 10,
                    "size": 4,
                    "startRow": 1,
                    "endRow": 4,
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

            action = reservableRoomList(arg)
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

        let arg: { roomId: number, reservedDate: Date };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {roomId: 1, reservedDate: new Date('2022-01-01')}
            result = {}

            action = reservableRoomCreate(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.create).toHaveBeenCalledWith(arg);
        })
    })

    describe('delete', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { roomId: number, reservedDate: Date };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {roomId: 1, reservedDate: new Date('2022-01-01')}
            result = {}

            action = reservableRoomDelete(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.delete).toHaveBeenCalledWith(arg);
        })
    })
})
