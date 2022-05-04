import React from "react";
import { Link } from "react-router-dom";
import classes from "./SignupOption.module.css";
const SignupOptionItem = ({ icon, text, link, classNames = "" }) => {
  return (
    <li className={classes.SignupOptionItem}>
      {icon}
      <Link to={link}>{text}</Link>
    </li>
  );
};

export default SignupOptionItem;
