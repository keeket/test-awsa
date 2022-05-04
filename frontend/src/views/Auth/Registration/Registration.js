import React from "react";
import { useHistory } from "react-router-dom";
import SignupOptions from "../../../components/SignupOptions/SignupOptions";
import RegistrationSuccess from "./RegistrationSuccess";
import RegistrationForm from "./Form";
import classes from "./Registration.module.css";
const Registration = () => {
  const {
    location: { pathname },
  } = useHistory();
  const content =
    pathname === "/account" ? (
      <SignupOptions />
    ) : pathname === "/account/signup/success" ? (
      <RegistrationSuccess />
    ) : (
      <RegistrationForm />
    );
  return <div className={classes.Registration}>{content}</div>;
};

export default Registration;
