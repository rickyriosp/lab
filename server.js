const express = require("express");
const cors = require("cors");
const bcrypt = require("bcryptjs");
const knex = require("knex");
const { request } = require("express");

const register = require("./controllers/register");
const signin = require("./controllers/signin");
const profile = require("./controllers/profile");
const image = require("./controllers/image");

const db = knex({
  client: "pg",
  connection: {
    host: "127.0.0.1",
    user: "nas7ybruises",
    password: "password",
    database: "face-recognition-app",
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

app.listen(3000, () => {
  console.log("app is running on port 3000");
});
