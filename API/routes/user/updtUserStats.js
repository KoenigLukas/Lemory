const express = require('express');
const sql = require('../../db.js');
const router = express.Router();

router.post('/', function(req, res, next) {
    res.send('PLEASE KILL ME NOW ');
});




module.exports = router;

