const express = require("express");
const cors = require("cors");
const bcrypt = require("bcryptjs");
const knex = require("knex");

const register = require("./controllers/register");
const signin = require("./controllers/signin");
const profile = require("./controllers/profile");
const image = require("./controllers/image");

require("dotenv").config();

// Development DB
// const db = knex({
//   client: "pg",
//   connection: {
//     host: "127.0.0.1",
//     user: "nas7ybruises",
//     password: "password",
//     database: "face-recognition-app",
//   },
// });

// Production DB
const db = knex({
  client: "pg",
  connection: {
    connectionString: process.env.DATABASE_URL,
    ssl: {
      rejectUnauthorized: false,
    },
  },
});

const app = express();
app.use(express.json());
app.use(cors());

app.get("/", (req, res) => {
  res.send("you are connected");
});

app.post("/signin", (req, res) => {
  // Dependency injection
  signin.handleSignin(req, res, db, bcrypt);
});

app.post("/register", (req, res) => {
  // Dependency injection
  register.handleRegister(req, res, db, bcrypt);
});

app.get("/profile/:id", (req, res) => {
  // Dependency injection
  profile.handleProfileGet(req, res, db, bcrypt);
});

app.put("/image", (req, res) => {
  // Dependency injection
  image.handleImage(req, res, db);
});

app.post("/imageurl", (req, res) => {
  // Dependency injection
  image.handleApiCall(req, res);
});

app.listen(process.env.PORT || 3000, () => {
  console.log(`app is running on port ${process.env.PORT}`);
});
