import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/contacts";

class ContactService {
    list() {
        return axios.get(API_URL, {headers: authHeader()});
    }

    createByMember(params: { contactId: string, details: string }) {
        return axios.post(API_URL, params, {headers: authHeader()});
    }

    createByGuest(params: { contactId: string, details: string }) {
        return axios.post(API_URL + '/guest', params, {headers: authHeader()});
    }
}

export default new ContactService();
