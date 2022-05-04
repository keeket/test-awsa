import React from "react";
import LogoImage from "../../../assets/logo.png";
import classes from "./Logo.module.css";
const Logo = ({ size = "100px" }) => {
  return (
    <div style={{ width: size }} className={classes.Logo}>
      <img src={LogoImage} alt='logo' />
    </div>
  );
};

export default Logo;
