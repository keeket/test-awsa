import React from "react";
import Card from "../../UI/Card/Card";
import JobOffersItem from "./JobOffersItem";
import { Google, Facebook, Dev } from "../../UI/CustomIcons";
import classes from "./JobOffers.module.css";
const JobOffers = () => {
  return (
    <div className='col-md-3'>
      <Card classNames={classes.JobOffers}>
        <header>
          <h3>Job Offers(7)</h3>
          <div className={classes.Actions}>
            <i className='fas fa-ellipsis-h'></i>
          </div>
        </header>
        <JobOffersItem icon={<Google />} />
        <JobOffersItem icon={<Facebook />} />
        <JobOffersItem icon={<Dev />} />
      </Card>
    </div>
  );
};

export default JobOffers;
