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

export const userUpdate = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'user/update',
    async (params: { userId: string; password: string; firstName: string; lastName: string, roleName: string }, {rejectWithValue}) => {
        try {
            return await UserService.update(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const userDelete = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'user/delete',
    async (params: { userId: string }, {rejectWithValue}) => {
        try {
            return await UserService.delete(params)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const roleNames = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors
    }>(
    'user/roleNames',
    async ({rejectWithValue}) => {
        try {
            return await UserService.roleNames()
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
    roleNames: string[]
    error: string | null | undefined
}

const initialState: SliceState = {
    users: {list: []},
    roleNames: [],
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
        builder.addCase(roleNames.fulfilled, (state, {payload}) => {
            state.roleNames = payload.data
        })
        builder.addCase(roleNames.rejected, (state, action) => {
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
