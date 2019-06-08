const express = require('express');
const sql = require('../db.js');
const router = express.Router();

router.post('/',function (req, res) {
  let user_id = req.body.id;
  let abc;
  // var token = req.query('token');

  sql.query(`SELECT * FROM user`, function (err, result, fields) {
    if (err) console.log(err);
    abc = result;
    console.log(result);
    res.send(result);
  });
});

module.exports = router;
