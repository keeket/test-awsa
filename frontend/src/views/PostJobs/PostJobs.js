import React from "react";
import LayoutContainer from "../../container/LayoutContainer";
import PostJobsContainer from "../../components/PostJobsCmp";
import PostJobsProviderWrapper from "./PostJobsReducerCmp";
import classes from "./PostJobs.module.css";
const PostJobs = () => {
  return (
    <PostJobsProviderWrapper>
      <div className={classes.PostJobs}>
        <LayoutContainer>
          <PostJobsContainer />
        </LayoutContainer>
      </div>
    </PostJobsProviderWrapper>
  );
};

export default PostJobs;
