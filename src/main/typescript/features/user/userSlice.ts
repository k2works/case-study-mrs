import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import UserService from "../../services/userService";
import {RootState} from "../../app/store";

interface ValidationErrors {
    message: string
}

export const userList = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'user/list',
    async ({rejectWithValue}) => {
        try {
            return await UserService.list()
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const userCreate = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'user/create',
    async (params: { userId: string; password: string; firstName: string; lastName: string, roleName: string }, {rejectWithValue}) => {
        try {
            return await UserService.create(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

interface User {
    userId: {
        value: string
    },
    password: {
        value: string
    },
    name: {
        firstName: string,
        lastName: string
    },
    roleName: string,
}

interface users {
    list: User[]
}

export type SliceState = {
    users: users
    error: string | null | undefined
}

const preDay = (date: Date) => {
    return new Date(date.setDate(date.getDate() - 1))
}

const nextDay = (date: Date) => {
    return new Date(date.setDate(date.getDate() + 1))
}

const initialState: SliceState = {
    users: {list: []},
    error: null
}

export const userSlice = createSlice({
    name: 'user',
    initialState: initialState,
    reducers: {},
    extraReducers: builder => {
        builder.addCase(userList.fulfilled, (state, {payload}) => {
            state.users = payload.data
        })
        builder.addCase(userList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
    }
})

export const userState = (state: RootState) => state.user
export default userSlice.reducer
