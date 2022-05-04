import validateEmail from "../../../utils/validateEmail";
export const initState = {
  isValid: false,
  email: {
    value: "",
    error: "",
  },
  password: {
    value: "",
    error: "",
  },
};

const checkValidity = (state, name) => {
  const cloneState = { ...state };

  switch (name) {
    case "email":
      if (!cloneState.email.value.trim()) {
        cloneState.email.error = `Email is required`;
      } else if (!validateEmail(cloneState.email.value)) {
        cloneState.email.error = `Invalid email address`;
      } else {
        cloneState.email.error = "";
      }
      return cloneState;
    case "password":
      if (!cloneState.password.value.trim()) {
        cloneState.password.error = `Password is required`;
      } else if (cloneState.password.value.length < 8) {
        cloneState.password.error = `Password must be 8 or more character`;
      } else {
        cloneState.password.error = "";
      }
      return cloneState;

    default:
      return cloneState;
  }
};

export const loginReducer = (state = initState, action) => {
  const cloneState = { ...state };
  switch (action.type) {
    case "INPUT_CHANGE":
      cloneState[action.name].value = action.value;
      const validateCloneState = cloneState[action.name].error
        ? checkValidity(cloneState, action.name)
        : cloneState;
      return validateCloneState;
    case "INPUT_BLUR":
      switch (action.name) {
        case "email":
          if (!cloneState.email.value.trim()) {
            cloneState.email.error = `Email is required`;
          } else if (!validateEmail(cloneState.email.value)) {
            cloneState.email.error = `Invalid email address`;
          } else {
            cloneState.email.error = "";
          }
          return cloneState;
        case "password":
          if (!cloneState.password.value.trim()) {
            cloneState.password.error = `Password is required`;
          } else if (cloneState.password.value.length < 8) {
            cloneState.password.error = `Password must be 8 or more character`;
          } else {
            cloneState.password.error = "";
          }
          return cloneState;

        default:
          return cloneState;
      }
    case "CHECK_VALIDITY":
      const inputKeys = Object.keys(cloneState).slice(1);
      let nowValid = true;
      inputKeys.forEach(key => {
        if (key === "email" && !validateEmail(cloneState[key].value)) {
          nowValid = false;
        } else if (key === "password" && cloneState.password.value.length < 8) {
          nowValid = false;
        } else if (cloneState[key].error || !cloneState[key].value.trim()) {
          nowValid = false;
        }
      });
      cloneState.isValid = nowValid;
      return cloneState;
    default:
      return state;
  }
};
