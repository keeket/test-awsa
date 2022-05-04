import React from "react";
import { Link } from "react-router-dom";
import classes from "./Button.module.css";
const ButtonPrimary = ({ children, block, disabled, link, onClick, type }) => {
  const allClasses = [
    classes.Button,
    classes.Primary,
    block ? classes.Block : "",
  ];
  const content = link ? (
    <Link
      onClick={onClick}
      className={allClasses.join(" ")}
      disabled={disabled}
      to={link}
    >
      {children}
    </Link>
  ) : (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className={allClasses.join(" ")}
    >
      {children}
    </button>
  );
  return content;
};

export default ButtonPrimary;
