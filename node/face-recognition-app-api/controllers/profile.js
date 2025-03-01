const handleProfileGet = (req, res, db, bcrypt) => {
  const { id } = req.params;
  return db
    .select("*")
    .from("users")
    .where({ id })
    .then((user) => {
      if (user.length) {
        res.json(user[0]);
      } else {
        res.status(400).json("user not found");
      }
    })
    .catch((err) => {
      res.status(400).json("error getting user");
    });
};

module.exports = {
  handleProfileGet: handleProfileGet,
};
