import React from "react";
import { Link } from "react-router-dom";
import classes from "./Button.module.css";
const ButtonSecondary = ({ children, block, disabled, link }) => {
  const allClasses = [
    classes.Button,
    classes.Secondary,
    block ? classes.Block : "",
  ];
  const content = link ? (
    <Link className={allClasses.join(" ")} disabled={disabled} to={link}>
      {children}
    </Link>
  ) : (
    <button disabled={disabled} className={allClasses.join(" ")}>
      {children}
    </button>
  );
  return content;
};

export default ButtonSecondary;
