import React from "react";
import QuestionTagsItem from "./QuestionTagsItem";
import classes from "./QuestionTags.module.scss";

const tags = [
  "Work Experience",
  "Education",
  "Location",
  "Language",
  "Licence",
  "Work Authorization",
  "Visa Status",
  "Tools",
  "Other Documents",
  "Custom Link",
];

const QuestionTags = () => {
  return (
    <ul className={classes.QuestionTags}>
      {tags.map(item => (
        <QuestionTagsItem key={item} name={item} />
      ))}
    </ul>
  );
};

export default QuestionTags;
