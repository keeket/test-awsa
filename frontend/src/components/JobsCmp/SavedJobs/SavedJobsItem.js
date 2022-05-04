import React from "react";
import CardInside from "../../UI/CardInside/CardInside";
import classes from "./SavedJobs.module.css";
const SavedJobsItem = ({ icon }) => {
  return (
    <CardInside classNames={classes.SavedJobsItem}>
      <span className={classes.Logo}>{icon}</span>
      <h4 className={classes.JobPosition}>Medium content writter</h4>
      <span className={classes.CloseIcon}>
        <i className='fas fa-times'></i>
      </span>
    </CardInside>
  );
};

export default SavedJobsItem;
