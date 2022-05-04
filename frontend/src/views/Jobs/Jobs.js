import React from "react";

import JobsContainer from "../../components/JobsCmp";
import LayoutContainer from "../../container/LayoutContainer";
import classes from "./Jobs.module.css";
const Jobs = () => {
  return (
    <div className={classes.Dashboard}>
      <LayoutContainer>
        <JobsContainer />
      </LayoutContainer>
    </div>
  );
};

export default Jobs;
