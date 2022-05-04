import React from "react";
import classes from "./ScreeningQuestions.module.scss";
const ScreeningQuestions = () => {
  return (
    <div className={classes.ScreeningQuestions}>
      <div className={classes.screeningContainer}>
        <h2>Your screening questions will appear here</h2>
        <p>Add screening questions to easily find your perfect candidates</p>
      </div>

      <div className={classes.RecommendText}>
        <h3>We recommend not to ask more than 2-4 screening questions</h3>
        <p>
          Focus on the most relevant screening questions to keep potential
          candidates motivated throughout the process
        </p>
      </div>
    </div>
  );
};

export default ScreeningQuestions;
