import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/reservableRooms";

class ReservableRoomService {
    list() {
        return axios.get(API_URL, {headers: authHeader()});
    }

    create(param: { roomId: number; reservedDate: Date }) {
        return axios.post(API_URL, param, {headers: authHeader()});
    }

    delete(params: { roomId: number; reservedDate: Date }) {
        return axios.delete(API_URL + "/" + params.roomId + "/" + params.reservedDate, {headers: authHeader()});
    }
}

export default new ReservableRoomService();
