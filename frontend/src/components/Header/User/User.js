import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import Avatar from "../../UI/Avatar/Avatar";
import classes from "./User.module.css";
import { logout } from "../../../actions/authActions";
const User = () => {
  const history = useHistory();
  const [userInfoActive, setUserInfoActive] = useState(false);
  const clickHandler = e => {
    if (e.target.tagName === "IMG") {
      setUserInfoActive(state => !state);
    }
  };
  const logoutHandler = e => {
    e.preventDefault();
    logout(history);
  };
  return (
    <li className={classes.User}>
      <ul className={classes.UserMenu}>
        <li>
          <span className={classes.Icon}>
            <i className='fas fa-cog'></i>
          </span>
        </li>
        <li>
          <span className={`${classes.Icon} ${classes.Notification}`}>
            <i className='far fa-bell'></i>
          </span>
        </li>
        <li onClick={clickHandler} className={`${classes.UserProfile}`}>
          <Avatar size={40} />
          <ul
            className={`${classes.AccountInfo}  ${
              userInfoActive ? classes.Active : ""
            }`}
          >
            <li>
              <Link to='/profile'>
                <span className={classes.Icon}>
                  <i className='fas fa-user'></i>
                </span>
                <span className={classes.Text}>Profile</span>
              </Link>
            </li>
            <li>
              <Link onClick={logoutHandler} to='/'>
                <span className={classes.Icon}>
                  <i className='fas fa-lock'></i>
                </span>
                <span className={classes.Text}>Logout</span>
              </Link>
            </li>
          </ul>
        </li>
      </ul>
    </li>
  );
};
export default User;
