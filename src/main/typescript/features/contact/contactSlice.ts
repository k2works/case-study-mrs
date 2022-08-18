import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import ContactService from "../../services/contactService";
import {RootState} from "../../app/store";

interface ValidationErrors {
    message: string;
}

export const contactList = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors;
    }>(
    'contact/list',
    async (params: any, {rejectWithValue}) => {
        try {
            return await ContactService.list(params.page);
        } catch (e: any) {
            if (!e.response) {
                throw e;
            }
            return rejectWithValue(e.response.data);
        }
    }
)

export const contactCreate = createAsyncThunk<any,
    any,
    {
        rejectValue: ValidationErrors;
    }>(
    'contact/create',
    async (params: { contactId: string; details: string; roleName: string }, {rejectWithValue}) => {
        try {
            if (params.roleName === "一般") {
                return await ContactService.createByMember({contactId: params.contactId, details: params.details});
            } else {
                return await ContactService.createByGuest({contactId: params.contactId, details: params.details});
            }
        } catch (e: any) {
            if (!e.response) {
                throw e;
            }
            return rejectWithValue(e.response.data);
        }
    }
)

interface Contact {
    contactId: {
        value: string
    },
    details: string
}

interface contacts {
    list: Contact[]
}

export type SliceState = {
    pageInfo: { total: number, pageNum: number, pageSize: number, pages: number }
    contacts: contacts
    error: string | null | undefined
}

const initialState: SliceState = {
    pageInfo: {total: 0, pageNum: 0, pageSize: 0, pages: 0},
    contacts: {list: []},
    error: null
}

export const contactSlice = createSlice({
    name: 'contact',
    initialState: initialState,
    reducers: {},
    extraReducers: builder => {
        builder.addCase(contactList.pending, (state, action) => {
            state.error = null;
        })
        builder.addCase(contactList.fulfilled, (state, action) => {
            state.pageInfo = action.payload.data;
            state.contacts = action.payload.data;
        })
        builder.addCase(contactList.rejected, (state, action) => {
            if (action.payload) {
                state.error = action.payload.message;
            } else {
                state.error = action.error.message;
            }
        })
    }
})

export const contactState = (state: RootState) => state.contact;
export default contactSlice.reducer;
