import React from "react";
import classes from "./Notification.module.css";
const Notification = () => {
  return (
    <li className={classes.Notification}>
      <span className={classes.Icon}>
        <i className='fas fa-bell'></i>
      </span>
      <span className={classes.NotificationCount}>5</span>
    </li>
  );
};

export default Notification;
