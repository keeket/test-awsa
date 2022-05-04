import React, { useContext } from "react";
// import CountrySelector from "../../CountrySelector/CountrySelector";
import Select from "../../UI/Select/Select";
// import Devices from "../../UI/Devices/Devices";
// import OSSelector from "../../UI/OSSelector/OSSelector";
import { postJobsContext } from "../../../views/PostJobs/PostJobsReducerCmp";
import classes from "./MetaInfo.module.css";

const softSkillsOpt = [
  { label: "Team work", value: "Team work" },
  { label: "Teaching", value: "Teaching" },
  { label: "Presentation", value: "Presentation" },
];
const hardSkillOpt = [
  { label: "Python", value: "Python" },
  { label: "Java", value: "Java" },
  { label: "C++", value: "C++" },
  { label: "JavaScript", value: "JavaScript" },
];

const MetaInfo = () => {
  const ctx = useContext(postJobsContext);

  // const [country, setCountry] = useState("");
  // const [region, setRegion] = useState("");
  // const [selectedDevices, setSelectedDevices] = useState([]);
  // const [osState, setOsState] = useState("");
  // const [oses, setOses] = useState([
  //   {
  //     label: "Every Os",
  //     value: "every os",
  //   },
  // ]);

  // const onCountrySelect = val => {
  //   setCountry(val);
  //   ctx.changeState(val, "COUNTRY");
  // };
  // const onRegionSelect = val => {
  //   ctx.changeState(val, "REGION");
  //   setRegion(val);
  // };
  // const selectedDeviceToggle = device => {
  //   let cloneDevices = [...selectedDevices];
  //   if (selectedDevices.includes(device)) {
  //     cloneDevices = cloneDevices.filter(item => item !== device);
  //   } else {
  //     cloneDevices.push(device);
  //   }
  //   ctx.changeState(cloneDevices, "DEVICES");
  //   setSelectedDevices(cloneDevices);
  // };
  // const changeOsStateHandler = state => {
  //   setOsState(state);
  //   ctx.changeState(state, "OS_STATE");
  // };
  // const changeOsesHandler = selectedOses => {
  //   setOses(selectedOses);
  //   ctx.changeState(selectedOses, "OS");
  // };

  return (
    <div className='col-md-8'>
      <div className={classes.SelectSoftSkill}>
        <Select
          label='Soft Skills'
          options={softSkillsOpt}
          value={ctx.softSkills}
          onChange={val => ctx.changeState(val, "SOFT_SKILLS")}
          isMulti
        />
      </div>
      <div className={classes.SelectHardSkill}>
        <Select
          label='Hard Skills'
          options={hardSkillOpt}
          value={ctx.hardSkills}
          onChange={val => ctx.changeState(val, "HARD_SKILLS")}
          isMulti
        />
      </div>
      {/* <div className={classes.CountrySelector}>
        <CountrySelector
          onCountrySelect={onCountrySelect}
          onRegionSelect={onRegionSelect}
          country={country}
          region={region}
        />
      </div>
      <div className={classes.Devices}>
        <Devices
          selectedDevices={selectedDevices}
          toggleDevices={selectedDeviceToggle}
        />
      </div>
      <div className={classes.OsSelector}>
        <OSSelector
          osState={osState}
          oses={oses}
          changeOsState={changeOsStateHandler}
          changeOsesOption={changeOsesHandler}
        />
      </div> */}
    </div>
  );
};

export default MetaInfo;
