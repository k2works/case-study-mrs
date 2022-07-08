import axios from "axios";
import {authConst} from "../app/const";

const API_URL = authConst.API_URL;

class AuthService {
    login(userId: any, password: any) {
        return axios
            .post(API_URL + "/signin", {userId, password})
            .then((response) => {
                if (response.data.token) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            })
            .catch(error => {
                throw error
            })
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(userId: any, email: any, password: any) {
        return axios
            .post(API_URL + "signup", {
                userId,
                email,
                password,
            })
            .then(response => {
                return response.data
            })
            .catch(error => {
                throw error
            })
    }
}

export default new AuthService();
