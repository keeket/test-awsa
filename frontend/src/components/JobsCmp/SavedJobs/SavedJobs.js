import React from "react";
import Card from "../../UI/Card/Card";
import SavedJobsItem from "./SavedJobsItem";
import { Medium, Facebook, Twitter } from "../../UI/CustomIcons";
import classes from "./SavedJobs.module.css";
const SavedJobs = () => {
  return (
    <div className='col-md-3'>
      <Card classNames={classes.SavedJobs}>
        <header>
          <h3>Saved Jobs(7)</h3>
          <div className={classes.Actions}>
            <i className='fas fa-ellipsis-h'></i>
          </div>
        </header>
        <SavedJobsItem icon={<Medium />} />
        <SavedJobsItem icon={<Facebook />} />
        <SavedJobsItem icon={<Twitter />} />
      </Card>
    </div>
  );
};

export default SavedJobs;
