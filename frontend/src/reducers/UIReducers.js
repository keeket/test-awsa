import * as actionTypes from "../actions/types/UiActionTypes";
export const toggleSideNavReducer = (
  state = {
    open: false,
  },
  action
) => {
  switch (action.type) {
    case actionTypes.TOGGLE_SIDENAV:
      return {
        open: !state.open,
      };
    default:
      return state;
  }
};
