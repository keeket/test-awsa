import React from "react";
import NewsItem from "./NewsItem";
import classes from "./News.module.css";
const News = () => {
  return (
    <div className={`col-md-6 ${classes.News}`}>
      <div className='row mb-4'>
        <div className={`col-sm-6 mb-4 mb-sm-0 ${classes.NewsItem}`}>
          <NewsItem>
            <div>
              <h4>
                <span className={classes.Icon}>
                  <i className='far fa-paper-plane'></i>
                </span>
                <span className={classes.Text}>4</span>
              </h4>
              <span className={classes.ItemName}>Invites</span>
            </div>
          </NewsItem>
        </div>
        <div className={`col-sm-6  ${classes.NewsItem}`}>
          <NewsItem>
            <h4>
              <span className={classes.Icon}>
                <i className='far fa-comment'></i>
              </span>
              <span className={classes.Text}>6</span>
            </h4>
            <span className={classes.ItemName}>Messages</span>
          </NewsItem>
        </div>
      </div>
      <div className='row'>
        <div className={`col-sm-6 mb-4 mb-sm-0 ${classes.NewsItem}`}>
          <NewsItem>
            <h4>
              <span className={classes.Icon}>
                <i className='fas fa-briefcase'></i>
              </span>
              <span className={classes.Text}>83</span>
            </h4>
            <span className={classes.ItemName}>fit Projects</span>
          </NewsItem>
        </div>
        <div className={`col-sm-6 ${classes.NewsItem}`}>
          <NewsItem>
            <h4>
              <span className={classes.Icon}>
                <i className='fas fa-briefcase'></i>
              </span>
              <span className={classes.Text}>8</span>
            </h4>
            <span className={classes.ItemName}>Alerts</span>
          </NewsItem>
        </div>
      </div>
    </div>
  );
};

export default News;
