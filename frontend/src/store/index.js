import { applyMiddleware, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";

import Thunk from "redux-thunk";
import RootReducer from "../reducers";
const middlewars = [Thunk];

export default createStore(
  RootReducer,
  composeWithDevTools(applyMiddleware(...middlewars))
);
