import React from "react";
import Card from "../../UI/Card/Card";
import JobAppliedItem from "./JobAppliedItem";
import { Google, LinkedIn, Dev } from "../../UI/CustomIcons";
import classes from "./JobApplied.module.css";
const JobApplied = () => {
  return (
    <div className='col-md-3'>
      <Card classNames={`${classes.JobApplied}`}>
        <header>
          <h3>Job Applied(21)</h3>
          <div className={classes.Actions}>
            <i className='fas fa-ellipsis-h'></i>
          </div>
        </header>
        <JobAppliedItem icon={<Google />} />
        <JobAppliedItem icon={<LinkedIn />} />
        <JobAppliedItem icon={<Dev />} />
      </Card>
    </div>
  );
};

export default JobApplied;
