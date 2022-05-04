import React from "react";
import { Link } from "react-router-dom";
import classes from "./SideNav.module.css";
const NavItem = ({ icon, text, link, active, navClose }) => {
  return (
    <li onClick={navClose}>
      <Link className={`${active ? classes.Active : ""}`} to={link}>
        <span className={classes.NavItemIcon}>
          <i className={icon}></i>
        </span>
        <span className={classes.NavItemText}>{text}</span>
      </Link>
    </li>
  );
};

export default NavItem;
