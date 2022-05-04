import * as actionTypes from "./types/UiActionTypes";

export const toggleSideNav = () => dispatch =>
  dispatch({ type: actionTypes.TOGGLE_SIDENAV });
