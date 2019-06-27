import sql = require('../../../db');

import * as express from 'express';
import * as Joi from 'joi';
import * as jwt from 'jsonwebtoken';
import {Request, Response} from "express";

const router = express.Router();

const loginSchema = {
    username: Joi.string().alphanum().min(3).max(15).required(),
    password: Joi.string().min(5).max(64).required()
};

router.post('/', function (req: Request, res: Response) {

    const validation: any = Joi.validate(req.body, loginSchema);
    if (validation.error) {
        res.status(400).send({
            success: false,
            message: validation.error.details[0].message
        });
        return;
    }

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
});



export = router;
