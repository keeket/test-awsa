import React from "react";
import { useDispatch } from "react-redux";
import Language from "./Language/Language";
import Notification from "./Notification/Notification";
import User from "./User/User";
import classes from "./Header.module.css";
import { toggleSideNav } from "../../actions/UiActions";

const Header = () => {
  const dispatch = useDispatch();
  const toggleHandler = () => {
    dispatch(toggleSideNav());
  };
  return (
    <header className={classes.Header}>
      <div onClick={toggleHandler} className={classes.Hamburger}>
        <i className='fas fa-bars'></i>
      </div>
      <ul className={classes.HeaderComponentList}>
        <Language />
        <Notification />
        <User />
      </ul>
    </header>
  );
};

export default Header;
