import React, { useEffect, useState } from "react";
import Avatar from "../../UI/Avatar/Avatar";
import Card from "../../UI/Card/Card";
import classes from "./Profile.module.css";

let timer;
const Profile = () => {
  const [i, setI] = useState(0);
  useEffect(() => {
    timer = setInterval(() => {
      setI(s => s + 1);
    }, 10);
  }, []);

  useEffect(() => {
    if (i === 70) {
      clearInterval(timer);
    }
  }, [i]);
  return (
    <div className='col-md-6'>
      <Card classNames={classes.Profile}>
        <div>
          <div className={classes.AvatarContainer}>
            <svg>
              <circle cy='70' cx='70' r='70'></circle>
              <circle
                style={{
                  strokeDashoffset: `calc(440 - (440 * ${i}) / 100)`,
                }}
                cy='70'
                cx='70'
                r='70'
              ></circle>
            </svg>
            <Avatar round size='100px' />
          </div>
          <h4 className={classes.UserName}>Earthgrob</h4>
          <span className={classes.UserLevel}>99 level</span>
        </div>
        <div className={classes.UserMetaInfo}>
          <div className={classes.Views}>
            <h3 className={classes.ViewsCount}>665</h3>
            <span>Views</span>
          </div>
          <div className={classes.Rated}>
            <h3 className={classes.RatedCount}>
              55<span>th</span>
            </h3>
            <span>Rated</span>
          </div>
        </div>
      </Card>
    </div>
  );
};

export default Profile;
