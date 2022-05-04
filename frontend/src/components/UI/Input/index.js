import React from "react";
import classes from "./Input.module.css";

const defaultPropertice = {
  name: "email",
  type: "email",
  placeholder: "Enter your email",
  id: "email",
};

const Input = ({
  htmlPropertice = defaultPropertice,
  label = "Email",
  errorText,
  register,
}) => {
  return (
    <div className={`${classes.Input} ${errorText ? classes.hasError : ""}`}>
      <label htmlFor={htmlPropertice.id}>{label}</label>
      <input {...htmlPropertice} {...register} />
      <span className={classes.errorText}>{errorText || "error text"}</span>
    </div>
  );
};

export default Input;
