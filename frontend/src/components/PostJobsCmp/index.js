import { useState, useContext } from "react";
import MetaInfo from "./MetaInfo/MetaInfo";
// import Budget from "./Budget/Budget";
import JobPreferences from "./JobPreferences/JobPreferences";
// import CPCCmp from "./CPCCmp/CPCCmp";
import { ButtonPrimary } from "../UI/Button";
import { postJobsContext } from "../../views/PostJobs/PostJobsReducerCmp";
import classes from "./PostJobs.module.css";
const PostJobsCmp = () => {
  const ctx = useContext(postJobsContext);
  const [showTargetOpt, setShowTargetOpt] = useState(false);
  const [showBudgetOpt, setShowBudgetOpt] = useState(false);
  const onSubmitHandler = () => {
    console.log(ctx);
  };
  return (
    <div className='mt-4'>
      <div className={classes.Target}>
        <h2 onClick={() => setShowTargetOpt(state => !state)}>
          Skillset
          <span
            className={`${classes.ArrowIcon} ${
              showTargetOpt ? classes.Active : ""
            }`}
          >
            <i className='fas fa-chevron-down'></i>
          </span>
        </h2>
        <div
          className={`${classes.BudgetContainer} ${
            showTargetOpt ? classes.show : classes.hide
          }`}
        >
          <div className='row'>
            <MetaInfo />
            {/* <CPCCmp /> */}
          </div>
        </div>
      </div>
      <hr />
      {/* <div className={classes.AdvancedTargeting}>
        <h3>
          <span className={classes.Icon}>
            <i className='fas fa-plus'></i>
          </span>
          Advanced Targeting
        </h3>
      </div> */}
      <div className={classes.Budget}>
        <h2 onClick={() => setShowBudgetOpt(state => !state)}>
          Job Preference
          <span
            className={`${classes.ArrowIcon} ${
              showBudgetOpt ? classes.Active : ""
            }`}
          >
            <i className='fas fa-chevron-down'></i>
          </span>
        </h2>
        <div
          className={`${classes.TargetContainer} ${
            showBudgetOpt ? classes.show : classes.hide
          }`}
        >
          <div className='row'>
            {/* <Budget /> */}
            <JobPreferences />
          </div>
        </div>
      </div>
      <ButtonPrimary onClick={onSubmitHandler}>Submit</ButtonPrimary>
    </div>
  );
};

export default PostJobsCmp;
