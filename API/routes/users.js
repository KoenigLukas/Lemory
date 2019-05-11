const express = require('express');
const sql = require('mysql');
const router = express.Router();


/* GET users listing. */
// router.get('/', function(req, res, next) {
//   var user_id = req.param('id');
//
//   console.log(user_id);
//
//    res.send(user_id);
// });

router.post('/', function(req, res) {
  let user_id = req.body.id;
  // var token = req.query('token');

  console.log(user_id);

  res.send("test");
});

module.exports = router;
