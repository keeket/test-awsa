import React from "react";

const QuestionTagsItem = ({ name }) => {
  return (
    <li>
      <i className='fas fa-plus'></i>
      {name}
    </li>
  );
};

export default QuestionTagsItem;
