import React from "react";
import Avatar from "../../UI/Avatar/Avatar";
import CardInside from "../../UI/CardInside/CardInside";
import classes from "./OnlineInterViews.module.css";
const InterViewItem = ({ schedule }) => {
  return (
    <CardInside classNames={classes.InterViewItem}>
      <div className={classes.Content}>
        <span className={classes.Avatar}>
          <Avatar size='40px' />
        </span>
        <h5 className={classes.UserName}>
          Kevin Gamer
          <span className={classes.UserAddress}>
            <i className='fas fa-map-marker-alt'></i>
            California, US
          </span>
        </h5>
      </div>
      <footer>
        {schedule ? (
          <div className={classes.ScheduleInterView}>
            <span className={classes.Icon}>
              <i className='fas fa-calendar-week'></i>
            </span>
            <span>Schedule Interview</span>
          </div>
        ) : (
          <>
            <p>Interview 10/05/21-10:30am</p>
            <div className={classes.Interviewers}>
              <Avatar size='30px' round />
              <Avatar size='30px' round />
            </div>
          </>
        )}
      </footer>
    </CardInside>
  );
};

export default InterViewItem;
