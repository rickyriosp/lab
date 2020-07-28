import "./App.css";

import ImageLinkForm from "./Components/ImageLinkForm/ImageLinkForm";
import Logo from "./Components/Logo/Logo";
import Navigation from "./Components/Navigation/Navigation";
import Particles from "react-particles-js";
import Rank from "./Components/Rank/Rank";
import React from "react";

const particlesOptions = {
  particles: {
    number: {
      value: 60,
      density: {
        enable: true,
        value_area: 800,
      },
    },
  },
};

function App() {
  return (
    <div className="App">
      <Particles className="particles" params={particlesOptions} />
      <Navigation />
      <Logo />
      <Rank />
      <ImageLinkForm />
      {/* <FaceRecognition />} */}
    </div>
  );
}

export default App;
