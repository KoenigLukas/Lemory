import * as express from 'express';

import sql = require('../../../db');
import * as Joi from 'joi';
import * as jwt from 'jsonwebtoken';
import {Request, Response} from "express";

const router = express.Router();

const statsSchema = {
    won: Joi.boolean().required(),
    time: Joi.number().required()
};

router.post('/', function(req: Request, res: Response, next) {
    // @ts-ignore
    if(!req.header('token')){
        res.status(400).send("no token provided");
        return;
    }

    console.log(req.header('token'));

    let tokenData: any;

    // @ts-ignore
    jwt.verify(req.header('token'), process.env.SECRET, function(err: any, decoded: any) {
        if (err) {
        res.status(401).send({
            success: false,
            message: 'Failed to authenticate token.'
        });
        }
        else{
            sql.query(`INSERT INTO stats(usernr,won,time) VALUES(?,?,?)`,
                [decoded.id,req.body.won,req.body.time],
                (err: any, result: any, fields: any) => {
                    if(err){
                        res.status(500).send({success: false, message: err.message});
                    }else{
                        res.status(200).send({success: true, message: "successfully added"})
                    }
            });
        }
    });
});




export = router;