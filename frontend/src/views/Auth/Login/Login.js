import React, { useReducer, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import Input from "../../../components/UI/Input";
import LoadingState from "../../../components/UI/LoadingState/LoadingState";
import { ButtonPrimary } from "../../../components/UI/Button";
import classes from "./Login.module.css";
import { initState, loginReducer } from "./loginReducer";
import { login } from "../../../actions/authActions";
const Login = () => {
  const history = useHistory();
  const { loading } = useSelector(state => state.loginUser);
  const dispatch = useDispatch();
  const [inputState, inputDispatch] = useReducer(loginReducer, initState);
  useEffect(() => {
    inputDispatch({ type: "CHECK_VALIDITY" });
  }, []);

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
      email: inputState.email.value,
      password: inputState.password.value,
    };
    dispatch(login(body, history));
  };
  return (
    <div className={classes.Login}>
      <Link to='/account' className={classes.BackLink}>
        <i className='fas fa-reply'></i>
      </Link>
      <h3 className={classes.Heading}>Log in</h3>
      <form onSubmit={submitHandler}>
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
        <ButtonPrimary disabled={!inputState.isValid || loading} block>
          {loading ? <LoadingState /> : "Log in"}
        </ButtonPrimary>
      </form>
    </div>
  );
};

export default Login;
