import * as express from 'express';
import sql = require('../../db');

import * as Joi from 'joi';
import * as jwt from 'jsonwebtoken';
import {Request, Response} from "express";

const router = express.Router();

//TODO: CAHNGE user_available AND email_available TO BOOLEAN

const registerSchema = {
    user_available: Joi.boolean().required(),
    email_available: Joi.boolean().required(),
    username: Joi.string().alphanum().min(3).max(15).required(),
    password: Joi.string().min(5).max(30).required(),
    email: Joi.string().email().required(),
    first_name: Joi.string().min(1).required(),
    last_name: Joi.string().min(1).required(),
    birth_date: Joi.string().regex(/([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/).required()
};

router.post('/', function(req: Request, res: Response, next) {

    const validation: any = Joi.validate(req.body, registerSchema);
    if (validation.error) {
        res.status(400).send({
            success: false,
            message: validation.error.details[0].message
        });
        return;
    }

    if(!(req.body.user_available && req.body.email_available)){
        res.status(403).send({
            success: false,
            message: "usernaem or password unavailable"
        });
        return;
    }

    sql.query(`INSERT INTO user(username,passwd,email,first_name,last_name,geburtstag) VALUES(?,?,?,?,?,?)`,
        [req.body.username, req.body.password, req.body.email, req.body.first_name, req.body.last_name, req.body.birth_date],
        (err: any, result: any, fields: any) => {
            if (err){
                res.status(500).send({
                    success: false,
                    message: err.message
                });
            }
            else{
                sql.query(`SELECT usernr,username FROM user WHERE (username = ? AND passwd = ?)`,
                    [req.body.username,req.body.password],
                    (err: any, result: any, fields: any) => {
                    if (err) {

                        res.status(500).send({
                            success: false,
                            message: err.message
                        });

                    } else if (!(result.length > 0)) {

                        res.status(200).send({
                            success: false,
                            message: "wrong username or password"
                        });

                    } else {

                        // @ts-ignore
                        const token: String = jwt.sign({id: result[0].usernr, username: result[0].username}, process.env.SECRET);
                        res.status(200).send(
                            {
                                success: true,
                                token: token
                            });

                    }
                });
            }
    });
});

router.get('/checkEmail/:email', function(req: Request, res: Response, next) {
    sql.query(`SELECT usernr FROM user WHERE email = ?`,[req.params['email']],  (err, result, fields) => {
        if (err) {

            res.status(500).send(err.message);

        } else if (!(result.length > 0)) {

            res.status(200).send({success: true,available: true, message: "available"});

        } else {

            res.status(200).send({success: true,available: false, message: "not available"});

        }
    });
});

router.get('/checkUsername/:username', function(req, res, next) {

    sql.query(`SELECT usernr FROM user WHERE username = ?`,[req.params['username']],  (err, result, fields) => {
        if (err) {

            res.status(500).send(err.message);

        } else if (!(result.length > 0)) {

            res.status(200).send({success: true,available: true, message: "available"});

        } else {

            res.status(200).send({success: true,available: false, message: "not available"});

        }
    });

});

export = router;