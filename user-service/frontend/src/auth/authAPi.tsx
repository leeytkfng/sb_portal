import axios from "axios";

const API_BASE_URL = "http://localhost:8090/api/auth";

export const login = async (email:string, password: string) =>{
    return axios.post(`${API_BASE_URL}/login`, {email,password});
}

export const register  = async (name:string,email:string,password:string) => {
    return axios.post(`${API_BASE_URL}/register`, {name, email, password})
}