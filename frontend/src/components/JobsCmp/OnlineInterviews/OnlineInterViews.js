import React from "react";
import Card from "../../UI/Card/Card";
import InterviewItem from "./InterviewItem";
import classes from "./OnlineInterViews.module.css";
const OnlineInterviews = () => {
  return (
    <div className='col-md-3'>
      <Card classNames={classes.OnlineInterviews}>
        <header>
          <h3>Scheduled Interviews</h3>
          <div className={classes.Actions}>
            <i className='fas fa-ellipsis-h'></i>
          </div>
        </header>
        <InterviewItem />
        <InterviewItem schedule />
        <InterviewItem />
      </Card>
    </div>
  );
};

export default OnlineInterviews;
