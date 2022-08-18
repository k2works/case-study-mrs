import apiModule from "../../services/userService";
import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import {userCreate, userDelete, userList, userUpdate} from "./userSlice";

jest.mock('../../services/userService')

describe('room reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/userService')
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
                    "total": 7,
                    "list": [
                        {
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
                        },
                        {
                            "userId": {
                                "value": "U000002"
                            },
                            "password": {
                                "value": "$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG"
                            },
                            "name": {
                                "firstName": "Bbb",
                                "lastName": "Bbb"
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
                        },
                        {
                            "userId": {
                                "value": "U000003"
                            },
                            "password": {
                                "value": "$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG"
                            },
                            "name": {
                                "firstName": "Ccc",
                                "lastName": "Ccc"
                            },
                            "roleName": "管理者",
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
                        },
                        {
                            "userId": {
                                "value": "U000004"
                            },
                            "password": {
                                "value": "$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG"
                            },
                            "name": {
                                "firstName": "山田",
                                "lastName": "太郎"
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
                        },
                        {
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
                        },
                        {
                            "userId": {
                                "value": "U000009"
                            },
                            "password": {
                                "value": "$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu"
                            },
                            "name": {
                                "firstName": "John",
                                "lastName": "Doe"
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
                        },
                        {
                            "userId": {
                                "value": "U000010"
                            },
                            "password": {
                                "value": "$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu"
                            },
                            "name": {
                                "firstName": "Mr",
                                "lastName": "X"
                            },
                            "roleName": "管理者",
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

            action = userList(arg)
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


    describe('create', () => {
        let action: AsyncThunkAction<void, {}, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: { userId: string, password: string, firstName: string, lastName: string, roleName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {userId: "U000001", password: "pAssw0rd", firstName: "テスト", lastName: "太郎", roleName: "一般"};
            result = {}

            action = userCreate(arg)
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

        let arg: { userId: string, password: string, firstName: string, lastName: string, roleName: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {userId: "U000001", password: "pAssw0rd", firstName: "テスト更新", lastName: "太郎更新", roleName: "管理者"};
            result = {}

            action = userUpdate(arg)
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

        let arg: { userId: string };
        let result: any;

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {userId: "U000001"};
            result = {}

            action = userDelete(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.delete).toHaveBeenCalledWith(arg);
        })
    })
})
