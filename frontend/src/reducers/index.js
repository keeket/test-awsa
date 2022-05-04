import { combineReducers } from "redux";
import { toggleSideNavReducer } from "./UIReducers";
import { registerReducer, loginReducer, authReducer } from "./authReducers";
export default combineReducers({
  //UI
  toggleNav: toggleSideNavReducer,
  //AUTH
  auth: authReducer,
  registerUser: registerReducer,
  loginUser: loginReducer,
});
