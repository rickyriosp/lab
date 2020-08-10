const express = require("express");
const cors = require("cors");
// const bcrypt = require("bcryptjs");

const app = express();
app.use(express.json());
app.use(cors());

const database = {
  users: [
    {
      id: "123",
      name: "John",
      email: "john@gmail.com",
      password: "cookies",
      entries: 0,
      joined: new Date(),
    },
    {
      id: "124",
      name: "Sally",
      email: "sally@gmail.com",
      password: "bananas",
      entries: 0,
      joined: new Date(),
    },
  ],
  login: [
    {
      id: "123",
      hash: "",
      email: "john@gmail.com",
    },
  ],
};

app.get("/", (req, res) => {
  res.send(database.users);
});

app.post("/signin", (req, res) => {
  if (
    req.body.email === database.users[0].email &&
    req.body.password === database.users[0].password
  ) {
    res.json("success");
  } else {
    res.status(404).json("error login in");
  }
});

app.post("/register", (req, res) => {
  const { email, name, password } = req.body;

  database.users.push({
    id: "125",
    name: name,
    email: email,
    password: password,
    entries: 0,
    joined: new Date(),
  });
  res.json(database.users[database.users.length - 1]);
});

app.get("/profile/:id", (req, res) => {
  const { id } = req.params;
  const users = database.users.filter((user) => {
    if (user.id === id) {
      return user;
    }
  });

  if (users.length !== 0) {
    res.json(users[0]);
  } else {
    res.status(400).json("user not found");
  }
});

app.post("/image", (req, res) => {
  const { id } = req.body;
  const users = database.users.filter((user) => {
    if (user.id === id) {
      return user;
    }
  });

  if (users.length !== 0) {
    users[0].entries++;
    res.json(users[0].entries);
  } else {
    res.status(400).json("user not found");
  }
});

// Generate Salt to encrypt hash
// bcrypt.genSalt(10, function (err, salt) {
//   bcrypt.hash(password, salt, function (err, hash) {
//     // Store hash in your password DB.
//     console.log(hash);
//   });
// });

// Load hash from your password DB.
// bcrypt.compare("B4c0/\/", hash, function(err, res) {
//   // res === true
// });
// bcrypt.compare("not_bacon", hash, function(err, res) {
//   // res === false
// });

// Load hash from your password DB.
// As of bcryptjs 2.4.0, compare returns a promise if callback is omitted:
// bcrypt.compare("B4c0//", hash).then((res) => {
//   // res === true
// });

app.listen(3000, () => {
  console.log("app is running on port 3000");
});

/*

/ --> res = this is working
/signin --> POST = success/fail
/register --> POST = user
/profile/:userId --> GET = user
/image --> PUT = user

*/
