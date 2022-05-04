import React from "react";
import classes from "./Devices.module.css";

const Devices = ({ selectedDevices, toggleDevices }) => {
  return (
    <div className={classes.Devices}>
      <h4 className={classes.title}>Devices</h4>
      <ul className={classes.DevicesContainer}>
        <li
          className={`${classes.DeviceItem} ${
            selectedDevices.includes("mobile") ? classes.selected : ""
          }`}
          onClick={toggleDevices.bind(null, "mobile")}
        >
          <span className={classes.Icon}>
            <i className='fas fa-mobile-alt'></i>
          </span>
          <span className={classes.DeviceName}>Mobile</span>
        </li>
        <li
          className={`${classes.DeviceItem} ${
            selectedDevices.includes("tablate") ? classes.selected : ""
          }`}
          onClick={toggleDevices.bind(null, "tablate")}
        >
          <span className={classes.Icon}>
            <i className='fas fa-tablet-alt'></i>
          </span>
          <span className={classes.DeviceName}>Tablate</span>
        </li>
        <li
          className={`${classes.DeviceItem} ${
            selectedDevices.includes("desktop") ? classes.selected : ""
          }`}
          onClick={toggleDevices.bind(null, "desktop")}
        >
          <span className={classes.Icon}>
            <i className='fas fa-desktop'></i>
          </span>
          <span className={classes.DeviceName}>Desktop</span>
        </li>
      </ul>
    </div>
  );
};

export default Devices;
