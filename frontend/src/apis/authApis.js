import request from "./axios";

export const register = body => request.post("/api/register", body);
export const login = body => request.post("/api/login", body);
