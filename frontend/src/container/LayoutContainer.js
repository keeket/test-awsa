import React from "react";
import { Redirect } from "react-router-dom";
import SideNav from "../components/SideNav/SideNav";
import Header from "../components/Header/Header";
import classes from "./LayoutContainer.module.css";
const LayoutContainer = ({ children }) => {
  const id = localStorage.getItem("id");
  if (!id) {
    return <Redirect to='/account' />;
  }

  return (
    <div className='container-fluid'>
      <div className='row'>
        <SideNav />
        <main className={`col-md-10 ${classes.Main}`}>
          <Header />
          {children}
        </main>
      </div>
    </div>
  );
};

export default LayoutContainer;
