import React, { createContext, useReducer } from "react";

const initState = {
  // devices: [],
  // osState: "",
  // OS: [
  //   {
  //     label: "Every Os",
  //     value: "every os",
  //   },
  // ],
  // budgetType: "",
  // costPerClick: 0.05,
  // budget: "",
  // minCPC: 0,
  // recommendedCPC: 0,
  softSkills: [],
  hardSkills: [],
  jobPreference: [],
  companyPreference: [],
  salaryAim: "",
  yearsOfExperience: "",
  Location: {},

  changeState: (val, type) => {},
};

export const postJobsContext = createContext(initState);

const jobPostReducer = (state, action) => {
  switch (action.type) {
    case "COUNTRY":
      return {
        ...state,
        Location: { ...state.Location, Country: action.val },
      };
    case "REGION":
      return {
        ...state,
        Location: { ...state.Location, City: action.val },
      };
    case "SOFT_SKILLS":
      return {
        ...state,
        softSkills: action.val.map(item => item.value),
      };
    case "HARD_SKILLS":
      return {
        ...state,
        hardSkills: action.val.map(item => item.value),
      };

    case "JOB_PREFERENCE":
      return {
        ...state,
        jobPreference: action.val.map(item => item.value),
      };

    case "COMPARY_PREFERENCE":
      return {
        ...state,
        companyPreference: action.val.map(item => item.value),
      };
    case "SALARY_AIM":
      return {
        ...state,
        salaryAim: +action.val,
      };
    case "EXPERIENCE":
      return {
        ...state,
        yearsOfExperience: +action.val,
      };
    case "DEVICES":
      return {
        ...state,
        devices: action.val,
      };
    case "OS_STATE":
      return {
        ...state,
        osState: action.val,
      };
    case "OS":
      return {
        ...state,
        OS: action.val,
      };
    case "BUDGET_TYPE":
      return {
        ...state,
        budgetType: action.val,
      };
    case "COST_PER_CLICK":
      return {
        ...state,
        costPerClick: action.val,
      };
    case "BUDGET":
      return {
        ...state,
        budget: action.val,
      };
    case "MIN_CPC":
      return {
        ...state,
        minCPC: action.val,
      };
    case "RECOMMENDED_CPC":
      return {
        ...state,
        recommendedCPC: action.val,
      };
    default:
      return state;
  }
};

const PostJobsProvider = ({ children }) => {
  const [state, dispatch] = useReducer(jobPostReducer, initState);
  const changeState = (val, type) => {
    dispatch({ type, val });
  };
  const valueState = { ...state, changeState };
  return (
    <postJobsContext.Provider value={valueState}>
      {children}
    </postJobsContext.Provider>
  );
};
export default PostJobsProvider;
