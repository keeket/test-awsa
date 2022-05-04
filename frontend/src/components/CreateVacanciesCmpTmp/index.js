import React from "react";
import { ButtonPrimary, ButtonSecondary } from "../UI/Button";
import QuestionTags from "./QuestionTags/QuestionTags";
import ScreeningQuestions from "./ScreeningQuestions/ScreeningQuestions";
import JobItem from "./JobItem/JobItem";
import classes from "./index.module.scss";
const CreateVacanciesCmp = () => {
  return (
    <div className={classes.CreateVacanciesCmp}>
      <h1 className={classes.heading_primary}>Join</h1>
      <h2 className={classes.heading_secondary}>
        <span className={classes.top}>Application form</span>
        <span className={classes.bottom}>
          Help us target the right candidates
        </span>
      </h2>
      <JobItem />
      <div className={classes.q_text_box}>
        <h2>
          Ask candidates about their qualifications
          <span className={classes.icon}>
            <i className='fas fa-star'></i>primium feature
          </span>
        </h2>
        <p>
          Add screening question below to find the best candidates more easily.
        </p>
      </div>
      <QuestionTags />
      <ScreeningQuestions />
      <div className={classes.actionBox}>
        <span>
          <i className='far fa-eye'></i>Show Preview
        </span>
        <div className={classes.btnBox}>
          <ButtonSecondary>Save Draft</ButtonSecondary>
          <ButtonPrimary>Publish now</ButtonPrimary>
        </div>
      </div>
    </div>
  );
};

export default CreateVacanciesCmp;
