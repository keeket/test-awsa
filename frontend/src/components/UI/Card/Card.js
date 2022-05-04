import React from "react";
import classes from "./Card.module.css";
const Card = ({ children, classNames = "" }) => {
  const allClasses = `${classes.Card} ${classNames}`;
  return <div className={allClasses}>{children}</div>;
};

export default Card;
