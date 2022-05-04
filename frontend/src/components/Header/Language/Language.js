import React from "react";
import Flag from "../../../assets/netherlands.png";
import classes from "./Language.module.css";
const Language = () => {
  return (
    <li className={classes.Language}>
      <img src={Flag} alt='flag' />
      <span className={classes.Name}>Netherlands</span>
      <span className={classes.Icon}>
        <i className='fas fa-chevron-down'></i>
      </span>
    </li>
  );
};

export default Language;
