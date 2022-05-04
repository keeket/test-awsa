import React from "react";
import { Link } from "react-router-dom";
import SignupOptionItem from "./SignupOptionItem";
import { Facebook, Twitter, Envelop } from "../UI/CustomIcons";
import classes from "./SignupOption.module.css";
const SignupOptions = () => {
  return (
    <div className={classes.SignupOptions}>
      <h3 className={classes.SignupOptionsHeading}>Get Started</h3>
      <ul className={classes.SignupOptionList}>
        <SignupOptionItem
          icon={<Envelop />}
          text='Sign up with email'
          link='/account/signup'
        />
        <SignupOptionItem
          icon={<Twitter />}
          text='Sign up with twitter'
          link='/signup'
          classNames='icon twitter'
        />
        <SignupOptionItem
          icon={<Facebook />}
          text='Sign up with facebook'
          link='/signup'
          classNames='icon facebook'
        />
      </ul>
      <p>
        Already have an account? <Link to='/account/login'>Log in</Link>
      </p>
    </div>
  );
};

export default SignupOptions;
