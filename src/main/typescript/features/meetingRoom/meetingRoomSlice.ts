import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import MeetingRoomService from "../../services/meetingRoomService";
import {RootState} from "../../app/store";

interface ValidationErrors {
    message: string
}

interface MeetingRoom {
    roomId: { value: number }
    roomName: string
}

interface MeetingRooms {
    list: MeetingRoom[]
}

export type SliceState = {
    meetingRooms: MeetingRooms
    error: string | null | undefined
}

export const meetingRoomList = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/list',
    async ({rejectWithValue}) => {
        try {
            return await MeetingRoomService.list()
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const meetingRoomCreate = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/create',
    async (params: { roomId: number, roomName: string }, {rejectWithValue}) => {
        try {
            return await MeetingRoomService.create(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const meetingRoomUpdate = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/update',
    async (params: { roomId: number, roomName: string }, {rejectWithValue}) => {
        try {
            return await MeetingRoomService.update(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const meetingRoomDelete = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/delete',
    async (params: { roomId: number, roomName: string }, {rejectWithValue}) => {
        try {
            return await MeetingRoomService.delete(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

const initialState: SliceState = {
    meetingRooms: {list: []},
    error: null
}

export const meetingRoomSlice = createSlice({
    name: 'meetingRoom',
    initialState: initialState,
    reducers: {},
    extraReducers: builder => {
        builder.addCase(meetingRoomList.pending, (state, action) => {
            state.error = null
        })
        builder.addCase(meetingRoomList.fulfilled, (state, action) => {
            state.meetingRooms = action.payload.data
        })
        builder.addCase(meetingRoomList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
    }
})

export const meetingRoomState = (state: RootState) => state.meetingRoom

export default meetingRoomSlice.reducer
