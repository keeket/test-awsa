import React from "react";
import CreateVacanciesCmp from "../../components/CreateVacanciesCmp";
import LayoutContainer from "../../container/LayoutContainer";
import classes from "./CreateVacancies.module.css";
const CreateVacancies = () => {
  return (
    <LayoutContainer>
      <div className={classes.CreateVacancies}>
        <CreateVacanciesCmp />
      </div>
    </LayoutContainer>
  );
};

export default CreateVacancies;
