import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {Dispatch} from "react";
import AuthService from "../../services/authService";
import authService from "../../services/authService";
import {setMessage} from '../message/messageSlice';
import {RootState} from "../../app/store";

const user = JSON.parse(<string>localStorage.getItem("user"));

export interface User {
    id: string
    password: string
    email?: string
    userInfo?: {
        userId: { value: string }
        password: { value: string }
        name: {
            firstName: { value: string }
            lastName: { value: string }
        }
        roleName: string
    }
}

interface ValidationErrors {
    message: string
}

export const authRegister = createAsyncThunk<any,
    User,
    {
        rejectValue: ValidationErrors
    }>(
    'auth/register',
    async (user: User, {rejectWithValue}) => {
        try {
            return await AuthService.register(user.id, user.email, user.password)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const authLogin = createAsyncThunk<any,
    User,
    {
        rejectValue: ValidationErrors
    }>(
    'auth/login',
    async (user: User, {rejectWithValue}) => {
        try {
            return await AuthService.login(user.id, user.password)
        } catch (e: any) {
            if (!e.response) {
                throw e
            }
            return rejectWithValue(e.response.data)
        }
    }
)

export const registerAsync = (user: User) => (dispatch: Dispatch<any>) => {
    return AuthService.register(user.id, user.email, user.password).then(
        (response) => {
            dispatch(register(user));
            dispatch(setMessage(response.data.message));
            return Promise.resolve();
        },
        (error) => {
            const message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            dispatch(register(user));
            dispatch(setMessage(message));
            return Promise.reject();
        }
    );
}

export type SliceState = {
    isLoggedIn: boolean
    user: User | null
    error: string | null | undefined
}

const initialState: SliceState = user
    ? {isLoggedIn: true, user, error: null}
    : {isLoggedIn: false, user: null, error: null};

export const authSlice = createSlice({
    name: 'auth',
    initialState: initialState,
    reducers: {
        register: (state, action) => {
            state.isLoggedIn = false
            state.user = action.payload.user
        },
        logout: state => {
            authService.logout()
            state.isLoggedIn = false
            state.user = null
        }
    },
    extraReducers: builder => {
        builder.addCase(authRegister.fulfilled, (state, {payload}) => {
            state.isLoggedIn = false
        })
        builder.addCase(authRegister.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
        })
        builder.addCase(authLogin.fulfilled, (state, action) => {
            state.isLoggedIn = true
            state.user = action.payload
        })
        builder.addCase(authLogin.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message
            } else {
                state.error = action.error.message
            }
            state.user = null
        })
    }
})

export const currentUser = (state: RootState) => state.auth.user

export const {register, logout} = authSlice.actions

export default authSlice.reducer
