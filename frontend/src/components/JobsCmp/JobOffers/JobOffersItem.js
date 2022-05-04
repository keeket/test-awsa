import React from "react";
import CardInside from "../../UI/CardInside/CardInside";
import { ButtonPrimary, ButtonSecondary } from "../../UI/Button";
import classes from "./JobOffers.module.css";
const JobOffersItem = ({ icon }) => {
  return (
    <CardInside classNames={classes.JobOffersItem}>
      <header>
        <span className={classes.Logo}>{icon}</span>
        <h4 className={classes.JobPosition}>
          Interective design lead- google photo
        </h4>
      </header>
      <ul className={classes.JobFacilities}>
        <li>
          <div className={classes.Content}>
            <span>Employeement Term</span>
            <h4>8 Months</h4>
          </div>
          <div className={classes.Action}>
            <span className={`${classes.ActionButton} ${classes.ActionClose}`}>
              <i className='fas fa-times'></i>
            </span>
            <span className={`${classes.ActionButton} ${classes.ActionOk}`}>
              <i className='fas fa-check'></i>
            </span>
          </div>
        </li>
        <li>
          <div className={classes.Content}>
            <span>Salary Offer</span>
            <h4>$450.50 per day</h4>
          </div>
          <div className={classes.Action}>
            <span className={`${classes.ActionButton} ${classes.ActionClose}`}>
              <i className='fas fa-times'></i>
            </span>
            <span className={`${classes.ActionButton} ${classes.ActionOk}`}>
              <i className='fas fa-check'></i>
            </span>
          </div>
        </li>
        <li>
          <div className={classes.Content}>
            <span>Activities</span>
            <h4>Photoshop</h4>
          </div>
          <div className={classes.Action}>
            <span className={`${classes.ActionButton} ${classes.ActionClose}`}>
              <i className='fas fa-times'></i>
            </span>
            <span className={`${classes.ActionButton} ${classes.ActionOk}`}>
              <i className='fas fa-check'></i>
            </span>
          </div>
        </li>
      </ul>
      <footer>
        <ButtonSecondary>Reject</ButtonSecondary>
        <ButtonPrimary>Accept</ButtonPrimary>
      </footer>
    </CardInside>
  );
};

export default JobOffersItem;
