import React from "react";
import classes from "./LoadingState.module.css";
const LoadingState = () => {
  return (
    <div className={classes["lds-ellipsis"]}>
      <div></div>
      <div></div>
      <div></div>
    </div>
  );
};

export default LoadingState;
