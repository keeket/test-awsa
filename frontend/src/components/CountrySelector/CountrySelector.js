import React from "react";
import { CountryDropdown, RegionDropdown } from "react-country-region-selector";
import classes from "./CountrySelector.module.css";
const CountrySelector = ({
  onCountrySelect,
  onRegionSelect,
  country,
  region,
  label,
}) => {
  return (
    <>
      <label className={classes.label}>{label}</label>
      <div className={classes.CountrySelector}>
        <CountryDropdown
          className={classes.Country}
          value={country}
          onChange={onCountrySelect}
        />
        <RegionDropdown
          disableWhenEmpty={true}
          className={classes.Region}
          country={country}
          value={region}
          onChange={onRegionSelect}
          defaultOptionLabel='Select City'
          blankOptionLabel='Select City'
        />
      </div>
    </>
  );
};

export default CountrySelector;
