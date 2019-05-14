const express = require('express');
const sql = require('../../db.js');
const Joi = require('joi');
const jwt = require('jsonwebtoken');
const fs = require('fs');

const router = express.Router();
const secret = "B1E74A38BDE2934F3DA4C3B8F897F";

const loginSchema = {
    username: Joi.string().alphanum().min(3).max(15).required(),
    password: Joi.string().min(5).max(30).required()
};

router.post('/', function (req, res) {

    const validation = Joi.validate(req.body, loginSchema);
    if (validation.error) {
        res.status(400).send(validation.error.details[0].message);
        return;
    }

    sql.query(`SELECT UserNr,Username FROM user WHERE (username = ? AND passwd = ?)`,[req.body.username,req.body.password],  (err, result, fields) => {
        if (err) {

             res.status(400).send(err.message);

    } else if (!(result.length > 0)) {

        res.status(404).send("not found");

    } else {

        const token = jwt.sign({id: result[0].UserNr, username: result[0].Username}, secret);
        res.status(200).send({token: token});

    }
    });
});



module.exports = router;