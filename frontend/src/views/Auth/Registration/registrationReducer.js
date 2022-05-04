import validateEmail from "../../../utils/validateEmail";
const initState = {
  isValid: false,
  name: {
    value: "",
    error: "",
  },
  email: {
    value: "",
    error: "",
  },
  password: {
    value: "",
    error: "",
  },
  passwordConfirm: {
    value: "",
    error: "",
  },
};

const checkValidity = (state, name) => {
  const cloneState = { ...state };

  switch (name) {
    case "name":
      if (!cloneState.name.value.trim()) {
        cloneState.name.error = `Name is required`;
      } else {
        cloneState.name.error = "";
      }
      return cloneState;
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
    case "passwordConfirm":
      if (!cloneState.passwordConfirm.value.trim()) {
        cloneState.passwordConfirm.error = `Please confirm your password`;
      } else if (
        cloneState.password.value !== cloneState.passwordConfirm.value
      ) {
        cloneState.passwordConfirm.error = `Password are not match`;
      } else {
        cloneState.passwordConfirm.error = "";
      }
      return cloneState;
    default:
      return cloneState;
  }
};

const hasError = state => {
  let error = false;

  Object.keys(state)
    .slice(1)
    .forEach(key => {
      if (key === "email" && !validateEmail(state[key].value)) {
        return (error = true);
      }
      if (state[key].error || !state[key].value) {
        error = true;
      }
    });

  return error;
};

const registrationReducer = (state = initState, action) => {
  const cloneState = { ...state };
  switch (action.type) {
    case "INPUT_CHANGE":
      cloneState[action.name].value = action.value;

      let validateCloneState = cloneState[action.name].error
        ? checkValidity(cloneState, action.name)
        : cloneState;
      if (
        action.name === "passwordConfirm" &&
        validateCloneState.password.value.length > 0 &&
        validateCloneState.passwordConfirm.error
      ) {
        if (
          validateCloneState.passwordConfirm.value !==
          validateCloneState.password.value
        ) {
          validateCloneState.passwordConfirm.error = "Password not match";
        }
      }
      return validateCloneState;
    case "INPUT_BLUR":
      return checkValidity(cloneState, action.name);
    case "CHECK_VALIDITY":
      const inputKeys = Object.keys(cloneState).slice(1);
      let nowValid = true;
      inputKeys.forEach(key => {
        if (
          key === "passwordConfirm" &&
          cloneState.password.value &&
          !cloneState.password.error &&
          !hasError(cloneState)
        ) {
          nowValid =
            cloneState.passwordConfirm.value === cloneState.password.value;
        } else if (key === "email" && !validateEmail(cloneState[key].value)) {
          nowValid = false;
        } else {
          if (cloneState[key].error || !cloneState[key].value.trim()) {
            nowValid = false;
          }
        }
      });
      cloneState.isValid = nowValid;
      return cloneState;
    default:
      return state;
  }
};

export { registrationReducer, initState };
