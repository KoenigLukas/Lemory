const express = require('express');
const sql = require('mysql');
const router = express.Router();

router.get('/', function(req, res, next) {
    res.send('PLEASE KILL ME NOW ');
});

module.exports = router;