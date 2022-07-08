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
    async ({rejectWithValue}) => {
        try {
            return await ContactService.list();
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
    contacts: contacts
    error: string | null | undefined
}

const initialState: SliceState = {
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
