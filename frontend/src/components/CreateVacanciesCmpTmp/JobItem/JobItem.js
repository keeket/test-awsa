import React from "react";
import classes from "./JobItem.module.scss";
const JobItem = () => {
  return (
    <div className={classes.JobItem}>
      <h2 className={classes.heading}>UI/UX Designer</h2>
      <ul className={classes.info}>
        <li>
          <i className='fas fa-map-marker-alt'></i>Berlin, Germany
        </li>
        <li>
          <i className='fas fa-briefcase'></i>Employee
        </li>
        <li>
          <i className='fas fa-folder'></i>IT & Development
        </li>
      </ul>
    </div>
  );
};

export default JobItem;
