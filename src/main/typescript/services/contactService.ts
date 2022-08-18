import axios from "axios";
import authHeader from "./auth-header";
import {contactConst} from "../app/const";

const API_URL = contactConst.API_URL;

class ContactService {
    list(page: number = 0) {
        return axios.get(API_URL + "?page=" + page, {headers: authHeader()});
    }

    createByMember(params: { contactId: string, details: string }) {
        return axios.post(API_URL, params, {headers: authHeader()});
    }

    createByGuest(params: { contactId: string, details: string }) {
        return axios.post(API_URL + '/guest', params, {headers: authHeader()});
    }
}

export default new ContactService();
