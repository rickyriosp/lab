import "./FaceRecognition.css";

import React from "react";

const FaceRecognition = ({ box, imageUrl }) => {
  return (
    <div className="center ma pa3">
      <div className="absolute">
        <img
          id="inputImage"
          src={imageUrl}
          alt=""
          width="500px"
          height="auto"
        />
        {box.map((el, i) => {
          return (
            <div
              key={i}
              className="bounding-box"
              style={{
                top: el.topRow,
                right: el.rightCol,
                bottom: el.bottomRow,
                left: el.leftCol,
              }}
            ></div>
          );
        })}
      </div>
    </div>
  );
};

export default FaceRecognition;
