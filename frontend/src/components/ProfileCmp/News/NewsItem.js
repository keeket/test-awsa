import React from "react";
import Card from "../../UI/Card/Card";
import classes from "./News.module.css";
const NewsItem = ({ children }) => {
  return <Card classNames={classes.NewsItem}>{children}</Card>;
};

export default NewsItem;
