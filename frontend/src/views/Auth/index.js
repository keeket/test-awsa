import React from "react";
import { Switch, Route } from "react-router-dom";
import Registration from "./Registration/Registration";
import Login from "./Login/Login";
import Logo from "../../components/UI/Logo/Logo";
import classes from "./Auth.module.css";
const Auth = () => {
  return (
    <div className={classes.Auth}>
      <div className={classes.Left}>
        <div className={classes.Logo}>
          <Logo size='150px' />
        </div>
        <div className={classes.Content}>
          <h2 className='display-6 mb-5'>
            Emplement user onboarding experience with just a few click - update @1531
          </h2>
          <p className='lead'>
            It is a long established fact that a reader will be distracted by
            the readable content of a page when looking at its layout.
          </p>
        </div>
      </div>
      <div className={classes.Right}>
        <Switch>
          <Route path='/account/login' component={Login} />
          <Route path='/account' component={Registration} />
        </Switch>
      </div>
    </div>
  );
};

export default Auth;
