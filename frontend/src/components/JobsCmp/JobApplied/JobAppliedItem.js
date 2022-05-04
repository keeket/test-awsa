import React from "react";
import CardInside from "../../UI/CardInside/CardInside";
import classes from "./JobApplied.module.css";
const JobAppliedItem = ({ icon }) => {
  return (
    <CardInside classNames={classes.Job}>
      <header>
        {icon}
        <div className={classes.Actions}>
          <i className='fas fa-ellipsis-h'></i>
        </div>
      </header>
      <div className={classes.Content}>
        <h3 className={classes.JobPosition}>
          UX Design Lead- Visual Domain Video Production
        </h3>
        <span className={classes.JobCompany}>Google</span>
        <address className={classes.JobAddress}>Melbourn, Australia</address>
      </div>
      <footer>
        <span className={classes.JobPostAt}>3 days ago</span>
        <span className={classes.JobStatus}>Full Time</span>
      </footer>
    </CardInside>
  );
};
export default JobAppliedItem;
