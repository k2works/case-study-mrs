import apiModule from "../../services/roomService";
import {AsyncThunkAction, Dispatch} from "@reduxjs/toolkit";
import {roomList} from "./roomSlice";

jest.mock('../../services/roomService')

describe('room reducer', () => {
    let api: jest.Mocked<typeof apiModule>

    beforeAll(() => {
        api = apiModule as any;
    })

    afterAll(() => {
        jest.unmock('../../services/roomService')
    })

    describe('list', () => {
        let action: AsyncThunkAction<void, Date, {}>

        let dispatch: Dispatch;
        let getState: () => unknown;

        let arg: any;
        let result: any;
        const date = new Date()

        beforeEach(() => {
            dispatch = jest.fn();
            getState = jest.fn();

            api.list.mockClear();
            api.list.mockResolvedValue(result)

            arg = {reservedDate: date, page: 1}
            result = {
                "value":
                    [{
                        "reservableRoomId": {
                            "roomId": {
                                "value": 1
                            },
                            "reservedDate": {
                                "value": "2021-04-14"
                            }
                        },
                        "meetingRoom": {
                            "roomId": {
                                "value": 1
                            },
                            "roomName": {
                                "value": "新木場"
                            }
                        }
                    }, {
                        "reservableRoomId": {
                            "roomId": {
                                "value": 7
                            },
                            "reservedDate": {
                                "value": "2021-04-14"
                            }
                        },
                        "meetingRoom": {
                            "roomId": {
                                "value": 7
                            },
                            "roomName": {
                                "value": "有楽町"
                            }
                        }
                    }]
            }

            action = roomList(arg)
        })

        test('サービスを呼びだす', async () => {
            await action(dispatch, getState, undefined);
            expect(api.list).toHaveBeenCalledWith(date, 1);
        })

        test('予約一覧を取得する', async () => {
            const data = await action(dispatch, getState, undefined);
            expect(data.payload).toStrictEqual(result)
        })
    })
})
