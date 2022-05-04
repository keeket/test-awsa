import React from "react";
import Avatar from "react-avatar";
import DefaultUser from "../../../assets/default-user.jpg";
import classes from "./Avatar.module.css";
const AvatarCmp = ({
  size = "50px",
  textSize = 2.5,
  src = DefaultUser,
  name = "Earthgrob",
  round = "5px",
}) => {
  const colors = ["#FF254F", "#4CBE9F", "#0055FF", "#B4B9FF"];
  return (
    <div className={classes.Avatar}>
      <Avatar
        name={name}
        src={src}
        colors={colors}
        maxInitials={2}
        alt='user photo'
        size={size}
        textSizeRatio={textSize}
        round={round}
      />
    </div>
  );
};

export default AvatarCmp;
