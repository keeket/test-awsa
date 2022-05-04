import React, { useContext } from "react";
import { postJobsContext } from "../../../views/PostJobs/PostJobsReducerCmp";
import classes from "./Budget.module.css";
const Budget = () => {
  const ctx = useContext(postJobsContext);
  const budgetTypeChangeHandler = val => {
    ctx.changeState(val, "BUDGET_TYPE");
  };
  const CPChandler = e => {
    ctx.changeState(+e.target.value, "COST_PER_CLICK");
  };
  const budgetTypeChangedHandler = val => {
    ctx.changeState(val, "BUDGET");
  };
  return (
    <>
      <div className={`col-md-8 ${classes.BudgetContainer}`}>
        <div className={classes.BudgetType}>
          <h4>Type</h4>
          <ul>
            <li
              className={ctx.budgetType === "cpc" ? classes.selected : ""}
              onClick={budgetTypeChangeHandler.bind(null, "cpc")}
            >
              CPC
            </li>
            <li
              className={ctx.budgetType === "cpm" ? classes.selected : ""}
              onClick={budgetTypeChangeHandler.bind(null, "cpm")}
            >
              CPM
            </li>
          </ul>
        </div>
        <div className={classes.CostPerClick}>
          <h4>Cost per click</h4>
          <input
            onChange={CPChandler}
            type='number'
            name='cpc'
            defaultValue='0.05'
          />
        </div>
        <div className={classes.Budget}>
          <h4>Budget</h4>
          <ul>
            <li
              className={ctx.budget === "per-day" ? classes.selected : ""}
              onClick={budgetTypeChangedHandler.bind(null, "per-day")}
            >
              Per day
            </li>
            <li
              className={ctx.budget === "unlimited" ? classes.selected : ""}
              onClick={budgetTypeChangedHandler.bind(null, "unlimited")}
            >
              Unlimited
            </li>
          </ul>
        </div>
      </div>
      <div className='col-md-4'>
        <p className={classes.para}>
          Please, note that we do not recommmend settings less than{" "}
          <strong>$25 daily budget</strong>
        </p>
        <p className={classes.para}>
          In case the daily budget is less than $25 we do not bear any
          responsibility for possible overspend issues
        </p>
      </div>
    </>
  );
};

export default Budget;
