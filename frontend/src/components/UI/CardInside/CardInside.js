import React from "react";
import classes from "./CardInside.module.css";
const CardInside = ({ children, classNames }) => {
  const allClasses = `${classes.CardInside} ${classNames}`;
  return <div className={allClasses}>{children}</div>;
};

export default CardInside;
