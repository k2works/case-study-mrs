import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import RoomService from "../../services/roomService";
import {RootState} from "../../app/store";

interface ValidationErrors {
    message: string
}

export const roomList = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'room/list',
    async (params: any, {rejectWithValue}) => {
        try {
            return await RoomService.list(params.reservedDate, params.page)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

interface ReservableRoom {
    meetingRoom: {
        roomId: { value: number }
        roomName: string
    }
    reservableRoomId: {
        reservedDate: { value: number }
        roomId: { value: string }
    }
}

interface ReservableRooms {
    list: ReservableRoom[]
}

export type SliceState = {
    pageInfo: { total: number, pageNum: number, pageSize: number, pages: number }
    reservedDate: Date
    reservableRooms: ReservableRooms
    error: string | null | undefined
}

const preDay = (date: Date) => {
    return new Date(date.setDate(date.getDate() - 1))
}

const nextDay = (date: Date) => {
    return new Date(date.setDate(date.getDate() + 1))
}

const initialState: SliceState = {
    pageInfo: {total: 0, pageNum: 0, pageSize: 0, pages: 0},
    reservedDate: new Date(),
    reservableRooms: {list: []},
    error: null
}

export const roomSlice = createSlice({
    name: 'room',
    initialState: initialState,
    reducers: {
        incrementReservedDate: (state, action) => {
            state.reservedDate = nextDay(state.reservedDate)
        },
        decrementReservedDate: (state, action) => {
            state.reservedDate = preDay(state.reservedDate)
        }
    },
    extraReducers: builder => {
        builder.addCase(roomList.fulfilled, (state, {payload}) => {
            state.pageInfo = payload.data
            state.reservableRooms = payload.data
        })
        builder.addCase(roomList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
    }
})

export const roomState = (state: RootState) => state.room
export const currentReservedDate = (state: RootState) => state.room.reservedDate.toLocaleDateString('ja')

export const {incrementReservedDate, decrementReservedDate} = roomSlice.actions

export default roomSlice.reducer
