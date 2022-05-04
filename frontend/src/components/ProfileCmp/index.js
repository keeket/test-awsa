import React from "react";
import Profile from "./Profile/Profile";
import News from "./News/News";
import classes from "./ProfileCmp.module.css";
const ProfileCmp = () => {
  return (
    <div className={`container-fluid mt-4 ${classes.ProfileCmp}`}>
      <header>
        <h2 className='display-1'>Profile</h2>
      </header>
      <div className='row'>
        <Profile />
        <News />
      </div>
    </div>
  );
};

export default ProfileCmp;
