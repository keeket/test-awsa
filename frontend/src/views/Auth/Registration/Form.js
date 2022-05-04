import React, { useReducer } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import LoadingState from "../../../components/UI/LoadingState/LoadingState";
import { ButtonPrimary } from "../../../components/UI/Button";
import Input from "../../../components/UI/Input";
import classes from "./Registration.module.css";
import { registrationReducer, initState } from "./registrationReducer";
import { register } from "../../../actions/authActions";
const Form = () => {
  const history = useHistory();
  const { loading } = useSelector(state => state.registerUser);
  const dispatch = useDispatch();
  const [inputState, inputDispatch] = useReducer(
    registrationReducer,
    initState
  );

  // useEffect(() => {
  //   const timer = setTimeout(() => {
  //     inputDispatch({ type: "CHECK_VALIDITY" });
  //   }, 300);
  //   return () => clearTimeout(timer);
  // }, [inputState]);

  const inputChangeHandler = e => {
    const value = e.target.value;
    const name = e.target.name;
    inputDispatch({ type: "INPUT_CHANGE", value, name });
    inputDispatch({ type: "CHECK_VALIDITY" });
    // console.log(value, name);
  };
  const inputBlurHandler = e => {
    const name = e.target.name;
    inputDispatch({ type: "INPUT_BLUR", name });
  };
  const submitHandler = e => {
    e.preventDefault();
    const body = {
      fullname: inputState.name.value,
      email: inputState.email.value,
      password: inputState.password.value,
    };
    dispatch(register(body, history));
  };
  return (
    <div className={classes.RegistrationForm}>
      <Link to='/account' className={classes.BackLink}>
        <i className='fas fa-reply'></i>
      </Link>
      <h3 className={classes.Heading}>Create Your Account</h3>
      <form onSubmit={submitHandler}>
        <Input
          label='Name'
          htmlPropertice={{
            name: "name",
            type: "text",
            placeholder: "Enter Your Name",
            id: "name",
            value: inputState.name.value,
            onChange: inputChangeHandler,
            onBlur: inputBlurHandler,
          }}
          errorText={inputState.name.error}
        />
        <Input
          label='Email'
          htmlPropertice={{
            name: "email",
            type: "email",
            placeholder: "Enter Your Email",
            id: "email",
            value: inputState.email.value,
            onChange: inputChangeHandler,
            onBlur: inputBlurHandler,
          }}
          errorText={inputState.email.error}
        />
        <Input
          label='Password'
          htmlPropertice={{
            name: "password",
            type: "password",
            placeholder: "Enter Your Password",
            id: "password",
            value: inputState.password.value,
            onChange: inputChangeHandler,
            onBlur: inputBlurHandler,
          }}
          errorText={inputState.password.error}
        />
        <Input
          label='Confirm Password'
          htmlPropertice={{
            name: "passwordConfirm",
            type: "password",
            placeholder: "Retype Your Password",
            id: "passwordConfirm",
            value: inputState.passwordConfirm.value,
            onChange: inputChangeHandler,
            onBlur: inputBlurHandler,
          }}
          errorText={inputState.passwordConfirm.error}
        />
        <ButtonPrimary disabled={!inputState.isValid || loading} block>
          {loading ? <LoadingState /> : " Sign Up"}
        </ButtonPrimary>
      </form>
    </div>
  );
};

export default Form;
