import React from "react";
import { ButtonPrimary } from "../../../components/UI/Button";
import Card from "../../../components/UI/Card/Card";
import classes from "./Registration.module.css";
const RegistrationSuccess = () => {
  return (
    <Card classNames={classes.RegistrationSuccess}>
      <span className={classes.Icon}>
        <i className='fas fa-check'></i>
      </span>
      <h4 className='display-6'>Your Registration Completed!</h4>
      <ButtonPrimary link='/account/login'>Login</ButtonPrimary>
    </Card>
  );
};

export default RegistrationSuccess;
