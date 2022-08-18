import axios from "axios";
import authHeader from "./auth-header";
import {meetingRoomConst} from "../app/const";

const API_URL = meetingRoomConst.API_URL

class MeetingRoomService {
    list(page: number = 0) {
        return axios.get(API_URL + "?page=" + page, {headers: authHeader()});
    }

    create(param: { roomId: number; roomName: string }) {
        return axios.post(API_URL, param, {headers: authHeader()});
    }

    update(params: { roomId: number; roomName: string }) {
        return axios.put(API_URL + "/" + params.roomId, params, {headers: authHeader()});
    }

    delete(params: { roomId: number; roomName: string }) {
        return axios.delete(API_URL + "/" + params.roomId, {headers: authHeader()});
    }

    listbox() {
        return axios.get(API_URL + "/listbox", {headers: authHeader()});
    }
}

export default new MeetingRoomService();
