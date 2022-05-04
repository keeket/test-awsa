import React from "react";
import ReactSelect from "react-select";
import classes from "./Select.module.css";
const Select = ({ options, onChange, isMulti, id, label }) => {
  return (
    <div className={classes.Select}>
      <label htmlFor={id}>{label}</label>
      <ReactSelect
        className={classes["react-select-container"]}
        classNamePrefix='react-select'
        onChange={onChange}
        options={options}
        isMulti={isMulti}
        id={id}
      />
    </div>
  );
};

export default Select;
