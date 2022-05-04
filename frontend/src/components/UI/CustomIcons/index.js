import React from "react";
import googleImageIcon from "./imageIcons/google.png";
import classes from "./customIcons.module.css";
export const Facebook = () => (
  <span className={`${classes.icon} ${classes.facebook}`}>
    <i className={` fab fa-facebook-f`}></i>
  </span>
);
export const Twitter = () => (
  <span className={`${classes.icon} ${classes.twitter}`}>
    <i className={`fab fa-twitter`}></i>
  </span>
);
export const LinkedIn = () => (
  <span className={`${classes.icon} ${classes.linkedin}`}>
    <i className={`fab fa-linkedin-in`}></i>
  </span>
);
export const Dev = () => (
  <span className={`${classes.icon} ${classes.dev}`}>
    <i className={`fab fa-dev`}></i>
  </span>
);
export const Medium = () => (
  <span className={`${classes.icon} ${classes.medium}`}>
    <i className={`fab fa-medium`}></i>
  </span>
);
export const Envelop = () => (
  <span className={classes.icon}>
    <i className={`far fa-envelope`}></i>
  </span>
);

export const Google = () => (
  <span className={`${classes.icon} ${classes.google}`}>
    <img src={googleImageIcon} alt='google icon' />
  </span>
);
