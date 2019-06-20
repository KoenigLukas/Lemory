import * as express from 'express';

import sql = require('../../../db');
import * as Joi from 'joi';
import * as jwt from 'jsonwebtoken';
import {Request, Response} from "express";

const router = express.Router();

router.post('/', function(req: Request, res: Response, next) {
    // @ts-ignore
    if(!req.header('token')){
        res.status(400).send({
            success: false,
            message: "no token provided"
        });
        return;
    }

    // @ts-ignore
    jwt.verify(req.header('token'), process.env.SECRET, function(err: any, decoded: any) {
        if (err) {
            res.status(401).send({
                success: false,
                message: 'Failed to authenticate token.'
            });
        }
        else{
            sql.query(`SELECT AVG(CASE WHEN  won = TRUE THEN 1 ELSE 0 END) AS avgwon, AVG(time) AS avgtime FROM stats WHERE usernr = ?`,
                [decoded.id],
                (err: any, result: any, fields: any) => {
                    if(err){
                        res.status(500).send({
                            success: false,
                            message: err.message
                        });
                    }else{
                        res.status(200).send({
                            success: true,
                            won: result[0].avgwon,
                            time: result[0].avgtime
                        });
                    }
                });
        }
    });
});




export = router;
