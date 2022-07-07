import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/users";

class UserService {
    list() {
        return axios.get(API_URL, {headers: authHeader()});
    }

    create(param: { userId: string; password: string; firstName: string; lastName: string, roleName: string }) {
        return axios.post(API_URL, param, {headers: authHeader()});
    }

    update(param: { userId: string; password: string; firstName: string; lastName: string, roleName: string }) {
        return axios.put(API_URL + "/" + param.userId, param, {headers: authHeader()});
    }

    delete(param: { userId: string }) {
        return axios.delete(API_URL + "/" + param.userId, {headers: authHeader()});
    }

    roleNames() {
        return axios.get(API_URL + "/roleNames", {headers: authHeader()});
    }
}

export default new UserService();
