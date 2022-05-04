import React from "react";
import JobApplied from "./JobApplied/JobApplied";
import SavedJobs from "./SavedJobs/SavedJobs";
import JobOffers from "./JobOffers/JobOffers";
import OnlineInterviews from "./OnlineInterviews/OnlineInterViews";
import classes from "./index.module.css";
const DashboradContainer = () => {
  const allClasses = `${classes.DashboradContainer} container-fluid`;
  return (
    <div className={allClasses}>
      <header>
        <h2 className='display-1'>Jobs</h2>
      </header>
      <div className='row'>
        <JobApplied />
        <SavedJobs />
        <JobOffers />
        <OnlineInterviews />
      </div>
    </div>
  );
};

export default DashboradContainer;
