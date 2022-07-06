import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/meetingRooms";

class MeetingRoomService {
    list() {
        return axios.get(API_URL, {headers: authHeader()});
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
}

export default new MeetingRoomService();