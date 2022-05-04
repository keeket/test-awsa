import React from "react";
import LayoutContainer from "../../container/LayoutContainer";
import ProfileCmp from "../../components/ProfileCmp";
import classes from "./Profile.module.css";
const Profile = () => {
  return (
    <div className={classes.Profile}>
      <LayoutContainer>
        <ProfileCmp />
      </LayoutContainer>
    </div>
  );
};

export default Profile;
