import * as actionTypes from "../actions/types/authTypes";

export const authReducer = (state = { error: "", userInfo: {} }, action) => {
  switch (action.type) {
    case actionTypes.LOGIN_USER_SUCCESS:
      return {
        ...state,
        userInfo: action.payload,
      };
    case actionTypes.UPDATE_USER:
      return {
        ...state,
        userInfo: action.payload,
      };
    default:
      return state;
  }
};

export const registerReducer = (
  state = { loading: false, user: {}, error: "" },
  action
) => {
  switch (action.type) {
    case actionTypes.REGISTER_USER_REQUEST:
      return {
        ...state,
        loading: true,
      };
    case actionTypes.REGISTER_USER_SUCCESS:
      return {
        ...state,
        user: action.payload,
        loading: false,
      };
    case actionTypes.REGISTER_USER_FAIL:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };
    default:
      return state;
  }
};
export const loginReducer = (
  state = { loading: false, user: {}, error: "" },
  action
) => {
  switch (action.type) {
    case actionTypes.LOGIN_USER_REQUEST:
      return {
        ...state,
        loading: true,
      };
    case actionTypes.LOGIN_USER_SUCCESS:
      return {
        ...state,
        user: action.payload,
        loading: false,
      };
    case actionTypes.LOGIN_USER_FAIL:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };
    default:
      return state;
  }
};
