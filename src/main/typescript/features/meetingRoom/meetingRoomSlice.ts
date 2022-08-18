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
    pageInfo: { total: number, pageNum: number, pageSize: number, pages: number }
    meetingRooms: MeetingRooms
    meetingRoomListBox: { key: string, value: string }[]
    error: string | null | undefined
}

export const meetingRoomList = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/list',
    async (params: any, {rejectWithValue}) => {
        try {
            return await MeetingRoomService.list(params.page)
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

export const meetingRoomListBox = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'meetingRoom/listbox',
    async ({rejectWithValue}) => {
        try {
            return await MeetingRoomService.listbox()
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

const initialState: SliceState = {
    pageInfo: {total: 0, pageNum: 0, pageSize: 0, pages: 0},
    meetingRooms: {list: []},
    meetingRoomListBox: [],
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
            state.pageInfo = action.payload.data
            state.meetingRooms = action.payload.data
        })
        builder.addCase(meetingRoomList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
        builder.addCase(meetingRoomListBox.pending, (state, action) => {
            state.error = null
        })
        builder.addCase(meetingRoomListBox.fulfilled, (state, action) => {
            state.meetingRoomListBox = action.payload.data
        })
        builder.addCase(meetingRoomListBox.rejected, (state, action) => {
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
