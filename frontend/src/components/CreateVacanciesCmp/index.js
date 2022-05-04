import React from "react";
import Input from "../UI/Input";
import Select from "../UI/Select/Select";
import { CountryDropdown, RegionDropdown } from "react-country-region-selector";
import Button from "../UI/Button/ButtonPrimary";
import { useForm, Controller } from "react-hook-form";

import classes from "./vacancies.module.scss";

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

const CreateVacanciesCmp = () => {
  const {
    register,
    handleSubmit,
    watch,
    // formState: { errors },
    control,
  } = useForm();

  const onSubmitHandler = data => {
    console.log(data);
  };

  return (
    <div className='row'>
      <div className='col-md-8'>
        <div className={classes.vacancies}>
          <h2 className={classes.vacancies__title}>Create Vacancies</h2>
          <form
            className={classes.vacancies__form}
            onSubmit={handleSubmit(onSubmitHandler)}
          >
            <Input
              label='Title'
              htmlPropertice={{
                type: "text",
                name: "title",
                id: "title",
                placeholder: "Job Title",
              }}
              register={{ ...register("title") }}
            />
            <div className={classes.vacancies__select}>
              <Controller
                control={control}
                defaultValue={[]}
                name='softSkills'
                render={({ field: { onChange, value, name, ref } }) => (
                  <Select
                    inputRef={ref}
                    label='Soft Skills'
                    options={softSkillsOpt}
                    value={value}
                    onChange={val => onChange(val.map(v => v.value))}
                    isMulti
                  />
                )}
              />
            </div>
            <div className={classes.vacancies__select}>
              <Controller
                control={control}
                name='hardSkills'
                defaultValue={[]}
                render={({ field: { onChange, value, name, ref } }) => (
                  <Select
                    inputRef={ref}
                    label='Hard Skills'
                    options={hardSkillOpt}
                    value={value}
                    onChange={val => onChange(val.map(v => v.value))}
                    isMulti
                  />
                )}
              />
            </div>

            <Input
              label='Qualifications'
              htmlPropertice={{
                type: "text",
                name: "qualification",
                id: "qualification",
                placeholder: "Qualification",
              }}
              register={{
                ...register("qualifications"),
              }}
            />
            <Input
              label='Salary'
              htmlPropertice={{
                type: "number",
                name: "salary",
                id: "salary",
                placeholder: "Salary",

                min: 0,
              }}
              register={{
                ...register("salary"),
              }}
            />
            <Input
              label='Experience'
              htmlPropertice={{
                type: "number",
                name: "experience",
                id: "experience",
                placeholder: "Years of experience",
                min: 0,
              }}
              register={{
                ...register("experience"),
              }}
            />
            <div className={classes.vacancies__CountrySelector}>
              <Controller
                control={control}
                name='country'
                render={({ field: { onChange, value } }) => (
                  <CountryDropdown
                    className={classes.vacancies__country}
                    value={value}
                    onChange={val => onChange(val)}
                  />
                )}
              />
              <Controller
                control={control}
                name='city'
                render={({ field: { onChange, value } }) => (
                  <RegionDropdown
                    disableWhenEmpty={true}
                    className={classes.vacancies__city}
                    country={watch().country}
                    value={value}
                    onChange={val => onChange(val)}
                    defaultOptionLabel='Select City'
                    blankOptionLabel='Select City'
                  />
                )}
              />
            </div>
            <Button type='submit'>Submit</Button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default CreateVacanciesCmp;
