const express = require('express');
const sql = require('../../db.js');
const Joi = require('joi');
const jwt = require('jsonwebtoken');
const fs = require('fs');

const router = express.Router();

const secret = "B1E74A38BDE2934F3DA4C3B8F897F";

const loginschema = {
    username: Joi.string().min(4).required(),
    password: Joi.string().min(5).required()
};

router.post('/', async function (req, res) {

    const validation = Joi.validate(req.body, loginschema);
    console.log(req.body.password);

    if (validation.error) {
        res.status(400).send(validation.error.details[0].message);
        return;
    }


    sql.query(`SELECT * FROM user WHERE (username = "${req.body.username}" AND passwd = "${req.body.password}")`, function (err, result, fields) {
        if (err) {

             res.sendStatus(400).send(err.message);

        } else if (!(result.length > 0)) {

             res.send("not found");

        } else {

            const token = jwt.sign({id: result[0].userid, username: result[0].username}, secret);

            // console.log(result[0].Username);
            // console.log({id: result[0].UserNr, username: result[0].Username});
            res.send({token: token});

        }


    });




});



module.exports = router;