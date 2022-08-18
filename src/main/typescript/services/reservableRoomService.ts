import axios from "axios";
import authHeader from "./auth-header";
import {reservableRoomConst} from "../app/const";

const API_URL = reservableRoomConst.API_URL;

class ReservableRoomService {
    list(page: number = 0) {
        return axios.get(API_URL + '?page=' + page, {headers: authHeader()});
    }

    create(param: { roomId: number; reservedDate: Date }) {
        return axios.post(API_URL, param, {headers: authHeader()});
    }

    delete(params: { roomId: number; reservedDate: Date }) {
        return axios.delete(API_URL + "/" + params.roomId + "/" + params.reservedDate, {headers: authHeader()});
    }
}

export default new ReservableRoomService();
