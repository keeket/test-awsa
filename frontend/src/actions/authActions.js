import * as actionTypes from "./types/authTypes";
import * as authApis from "../apis/authApis";
import { setToken, removeToken } from "../utils/setAndGetTokes";
export const register = (body, history) => async dispatch => {
  dispatch({ type: actionTypes.REGISTER_USER_REQUEST });
  try {
    const { data } = await authApis.register(body);
    // console.log(data.body);
    if (!data.body) {
      alert("Registration Fail!");
      dispatch({
        type: actionTypes.REGISTER_USER_FAIL,
        payload: "Registration fail",
      });
    } else {
      dispatch({ type: actionTypes.REGISTER_USER_SUCCESS, payload: data.body });
      history.push("/account/signup/success");
    }
  } catch (error) {
    console.log(error);
    alert("Registration Fail!");
    dispatch({
      type: actionTypes.REGISTER_USER_FAIL,
      payload: error?.response?.data,
    });
  }
};
export const login = (body, history) => async dispatch => {
  dispatch({ type: actionTypes.LOGIN_USER_REQUEST });
  try {
    const { data } = await authApis.login(body);
    // console.log(data.body);
    if (!data.body) {
      alert("Login fail!");
      dispatch({
        type: actionTypes.LOGIN_USER_FAIL,
        payload: "Login fail",
      });
    } else {
      setToken(data.body);
      dispatch({ type: actionTypes.LOGIN_USER_SUCCESS, payload: data.body });
      history.push("/");
    }
  } catch (error) {
    console.log(error);
    alert("Login Fail!");
    dispatch({
      type: actionTypes.LOGIN_USER_FAIL,
      payload: error?.response?.data,
    });
  }
};
export const logout = history => {
  removeToken();
  history.push("/account");
};
