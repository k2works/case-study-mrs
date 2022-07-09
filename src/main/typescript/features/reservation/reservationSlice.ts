import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import ReservationService from "../../services/reservationService"
import {RootState} from "../../app/store";

interface ValidationErrors {
    message: string
}

export const reservationList = createAsyncThunk<any,
    {
        date: Date,
        roomId: number
    },
    {
        rejectValue: ValidationErrors
    }>(
    'reservation/list',
    async (params: { date: Date, roomId: number }, {rejectWithValue}) => {
        try {
            return await ReservationService.list(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const reservationReserve = createAsyncThunk<any,
    {
        date: Date,
        start: string,
        end: string,
        roomId: number,
        userId: string
    },
    {
        rejectValue: ValidationErrors
    }>(
    'reservation/Reserve',
    async (params: { date: Date, start: string, end: string, roomId: number, userId: string }, {rejectWithValue}) => {
        try {
            return await ReservationService.reserve(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const reservationCancel = createAsyncThunk<any,
    {
        date: Date,
        roomId: number,
        reservationId: number,
        userId: string
    },
    {
        rejectValue: ValidationErrors
    }>(
    'reservation/Cancel',
    async (params: { date: Date, roomId: number, reservationId: number, userId: string }, {rejectWithValue}) => {
        try {
            return await ReservationService.cancel(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

interface Reservation {
    reservableRoom: {
        meetingRoom: {
            roomId: { value: number }
            roomName: { value: string }
        }
        reservableRoomId: {
            reservedDate: { value: string }
            roomId: { value: number }
        }
    }
    reservationId: {
        value: number
    }
    reservedTime: {
        startTime: string
        endTime: string
    }
    user: {
        name: {
            firstName: string
            lastName: string
        }
        password: {
            value: string
        }
        roleName: []
        userId: { value: string }
    }
}

interface Reservations {
    list: Reservation[]
}

export type SliceState = {
    reservedDate: Date
    roomId: number
    roomName: string
    reservations: Reservations
    error: string | null | undefined
}

const initialState: SliceState = {
    reservedDate: new Date(),
    roomId: 0,
    roomName: '',
    reservations: {list: []},
    error: null
}

export const reservationSlice = createSlice({
    name: 'reservation',
    initialState: initialState,
    reducers: {
        setParams: (state, action) => {
            const {reservedDate, roomId, roomName} = action.payload
            state.reservedDate = new Date(reservedDate)
            state.roomId = roomId
            state.roomName = roomName
        },
    },
    extraReducers: builder => {
        builder.addCase(reservationList.fulfilled, (state, {payload}) => {
            state.reservations = payload.data
        })
        builder.addCase(reservationList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
    }
})

export const reservationState = (state: RootState) => state.reservation
export const currentReservedDate = (state: RootState) => state.reservation.reservedDate.toLocaleDateString('ja')

export const {setParams} = reservationSlice.actions

export default reservationSlice.reducer
