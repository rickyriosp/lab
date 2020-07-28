import Logo from "../Logo/Logo";
import React from "react";

const Navigation = () => {
  return (
    <div style={{ display: "flex", justifyContent: "flex-start" }}>
      <Logo />
      <nav style={{ marginLeft: "auto" }}>
        <p className="f3 link dim black underline pa3 pointer">Sign Out</p>
      </nav>
    </div>
  );
};

export default Navigation;
