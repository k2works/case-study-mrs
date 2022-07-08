let BASE_API_URL = "http://localhost:8080/api";
if (process.env.NODE_ENV === 'production') {
    BASE_API_URL = "https://ape2022-take14.herokuapp.com/api";
}

export namespace authConst {
    export const API_URL = `${BASE_API_URL}/auth`
}

export namespace contactConst {
    export const API_URL = `${BASE_API_URL}/contacts`
}

export namespace meetingRoomConst {
    export const API_URL = `${BASE_API_URL}/meetingRooms`
}

export namespace reservableRoomConst {
    export const API_URL = `${BASE_API_URL}/reservableRooms`
}

export namespace reservationConst {
    export const API_URL = `${BASE_API_URL}/reservations`
}

export namespace roomConst {
    export const API_URL = `${BASE_API_URL}/rooms`
}

export namespace userConst {
    export const API_URL = `${BASE_API_URL}/users`
}

