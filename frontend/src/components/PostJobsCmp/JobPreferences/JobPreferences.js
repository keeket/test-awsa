import React, { useContext } from "react";
import Input from "../../UI/Input";
import { postJobsContext } from "../../../views/PostJobs/PostJobsReducerCmp";
import Select from "../../UI/Select/Select";
import CountrySelector from "../../CountrySelector/CountrySelector";
import classes from "./JobPreferences.module.css";

const preference = [
  { label: "Hard work", value: "Hard work" },
  { label: "Remote work", value: "Remote work" },
  { label: "Education", value: "Education" },
];
const companyPreference = [
  { label: "Small", value: "Small" },
  { label: "Pension plan", value: "Pension plan" },
];

const JobPreferences = () => {
  const ctx = useContext(postJobsContext);
  return (
    <div className={["col-md-8", classes.JobPreferences].join(" ")}>
      <div className={classes.Selectcontainer}>
        <Select
          options={preference}
          isMulti
          label='Preference'
          onChange={val => ctx.changeState(val, "JOB_PREFERENCE")}
          id='Preference'
        />
      </div>
      <div className={classes.Selectcontainer}>
        <Select
          options={companyPreference}
          isMulti
          label='Company Preference'
          onChange={val => ctx.changeState(val, "COMPARY_PREFERENCE")}
          id='companyPreference'
        />
      </div>
      <div className={classes.InputContainer}>
        <Input
          label='Salary Aim'
          htmlPropertice={{
            type: "number",
            name: "salaryAim",
            id: "salaryAim",
            placeholder: "What is your salary aim",
            onChange: e => ctx.changeState(e.target.value, "SALARY_AIM"),
            value: ctx.salaryAim,
            min: 0,
          }}
        />
      </div>

      <div className={classes.InputContainer}>
        <Input
          label='Experience'
          htmlPropertice={{
            type: "number",
            name: "experience",
            id: "experience",
            placeholder: "Years of experience",

            onChange: e => ctx.changeState(e.target.value, "EXPERIENCE"),
            value: ctx.yearsOfExperience,
            min: 0,
          }}
        />
      </div>
      <div className={classes.Selectcontainer}>
        <CountrySelector
          label='Location'
          country={ctx.Location.Country}
          region={ctx.Location.City}
          onCountrySelect={val => ctx.changeState(val, "COUNTRY")}
          onRegionSelect={val => ctx.changeState(val, "REGION")}
        />
      </div>
    </div>
  );
};

export default JobPreferences;
