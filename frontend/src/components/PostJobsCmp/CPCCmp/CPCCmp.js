import React, { useContext } from "react";
import { postJobsContext } from "../../../views/PostJobs/PostJobsReducerCmp";
import classes from "./CPCCmp.module.css";
const CPCCmp = () => {
  const ctx = useContext(postJobsContext);

  return (
    <div className='col-md-4'>
      <div className={classes.FormControl}>
        <label htmlFor='min-cpc'>Min CPC</label>
        <input
          onChange={e => ctx.changeState(+e.target.value, "MIN_CPC")}
          id='min-cpc'
          type='number'
          name='min-cpc'
          placeholder='$0.000'
        />
      </div>
      <div className={classes.FormControl}>
        <label htmlFor='r-cpc'>Recommended CPC</label>
        <input
          onChange={e => ctx.changeState(+e.target.value, "RECOMMENDED_CPC")}
          id='r-cpc'
          type='number'
          name='r-cpc'
          placeholder='$0.000'
        />
      </div>
      <p className={classes.para}>
        For more information check out <a href='/'>nichpush inside</a>
      </p>
    </div>
  );
};

export default CPCCmp;
