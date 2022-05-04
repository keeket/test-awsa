import React from "react";
import Select from "react-select";
import classes from "./OSSelector.module.css";

const options = [
  { value: "Every Os", label: "every os" },
  { value: "android", label: "Android" },
  { value: "ios", label: "IOS" },
  { value: "windows", label: "Windows" },
  { value: "Linux", label: "Linux" },
];

const OSSelector = ({ osState, oses, changeOsState, changeOsesOption }) => {
  return (
    <div className={classes.OSSelector}>
      <h3 className={classes.title}>OS</h3>
      <div className={classes.OSSelectorContainer}>
        <ul className={classes.Type}>
          <li
            onClick={changeOsState.bind(null, "include")}
            className={`${classes.TypeItem} ${
              osState === "include" ? classes.selected : ""
            }`}
          >
            Include
          </li>
          <li
            onClick={changeOsState.bind(null, "exclude")}
            className={`${classes.TypeItem} ${
              osState === "exclude" ? classes.selected : ""
            }`}
          >
            Exclude
          </li>
        </ul>
        <div className={classes.Selector}>
          <Select
            className={classes["react-select-container"]}
            classNamePrefix='react-select'
            value={oses}
            onChange={changeOsesOption}
            options={options}
            isMulti
          />
        </div>
      </div>
    </div>
  );
};

export default OSSelector;
