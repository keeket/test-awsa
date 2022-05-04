import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import Logo from "../UI/Logo/Logo";
import NavItem from "./NavItem";
import classes from "./SideNav.module.css";
import { toggleSideNav } from "../../actions/UiActions";
const SideNav = () => {
  const { open } = useSelector(state => state.toggleNav);
  const {
    location: { pathname },
  } = useHistory();
  const dispatch = useDispatch();
  const navCloseHandler = () => {
    dispatch(toggleSideNav());
  };

  return (
    <>
      <div className='col-md-2'></div>
      <nav
        className={`col-md-2 ${classes.SideNav} ${open ? classes.Show : ""}`}
      >
        <span onClick={navCloseHandler} className={classes.CloseNav}>
          <i className='far fa-window-close'></i>
        </span>
        <Link className={classes.LogoLink} to='/'>
          <Logo />
        </Link>
        <ul className={classes.NavMenu}>
          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/"}
            link='/'
            icon='fas fa-home'
            text='Dashboard'
          />
          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/profile"}
            link='/profile'
            icon='fas fa-user'
            text='My Account'
          />
          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/jobs"}
            link='/jobs'
            icon='fas fa-briefcase'
            text='Jobs'
          />

          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/post-jobs"}
            link='/post-jobs'
            icon='fas fa-puzzle-piece'
            text='Post Jobs'
          />
          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/create-vacancies"}
            link='/create-vacancies'
            icon='fas fa-plus'
            text='Create Vacancies'
          />
          <NavItem
            navClose={navCloseHandler}
            active={pathname === "/settings"}
            link='/sittings'
            icon='fas fa-cog'
            text='Settings'
          />
        </ul>
      </nav>
    </>
  );
};

export default SideNav;
