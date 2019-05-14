const express = require('express');
const sql = require('../../db.js');
const Joi = require('joi');
const jwt = require('jsonwebtoken');

const router = express.Router();

const registerSchema = {
    user_available: Joi.number().valid('200','404').required(),
    email_available: Joi.number().valid('200','404').required(),
    username: Joi.string().alphanum().min(3).max(15).required(),
    password: Joi.string().min(5).max(30).required(),
    email: Joi.string().email().required(),
    first_name: Joi.string().min(1).required(),
    last_name: Joi.string().min(1).required(),
    birth_date: Joi.string().regex(/([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/)
};

router.post('/', function(req, res, next) {

    const validation = Joi.validate(req.body, registerSchema);
    if (validation.error) {
        res.status(400).send(validation.error.details[0].message);
        return;
    }

    if(!(req.body.user_available && req.body.email_available) || (req.body.user_available === 200) || (req.body.user_available === 200)){
        res.sendStatus(403);
        return;
    }

    sql.query(`INSERT INTO user VALUES(?,?,2,?,?,?,STR_TO_DATE(?)`,
        [req.body.username, req.body.password, req.body.email, req.body.first_name, req.body.last_name, req.body.birth_date],
        (err, result, fields) => {
            if (err){
                res.status(500).send(err.message);
            }
            else{
                sql.query(`SELECT UserNr,Username FROM user WHERE (username = ? AND passwd = ?)`,
                    [req.body.username,req.body.password],
                    (err, result, fields) => {
                    if (err) {

                        res.status(500).send(err.message);

                    } else if (!(result.length > 0)) {

                        res.status(404).send("not found");

                    } else {

                        const token = jwt.sign({id: result[0].UserNr, username: result[0].Username}, process.env.SECRET);
                        res.status(200).send({token: token});

                    }
                });
            }
    });
});

router.get('/checkEmail/:email', function(req, res, next) {
    sql.query(`SELECT UserNr FROM user WHERE email = ?`,[req.body.email],  (err, result, fields) => {
        if (err) {

            res.status(500).send(err.message);

        } else if (!(result.length > 0)) {

            res.status(404).send("available");

        } else {

            res.status(200).send("already taken");

        }
    });
});

router.get('/checkUsername/:username', function(req, res, next) {
    sql.query(`SELECT UserNr FROM user WHERE username = ?`,[req.body.username],  (err, result, fields) => {
        if (err) {

            res.status(500).send(err.message);

        } else if (!(result.length > 0)) {

            res.status(404).send("available");

        } else {

            res.status(200).send("already taken");

        }
    });
});

module.exports = router;