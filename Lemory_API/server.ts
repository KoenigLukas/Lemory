import express = require('express');
import logger = require("morgan");
import * as winston from "winston";
import expressWinston, { LoggerOptions } from 'express-winston';
import * as dotenv from "dotenv";

dotenv.config();

import registerRouter = require('./v1/routes/user/registerUser');
import loginRouter = require('./v1/routes/user/loginUser');
import getStatsRouter = require('./v1/routes/user/stats/getStats');
import putStatsRouter = require('./v1/routes/user/stats/putStats');
import getBestTimeRouter = require('./v1/routes/user/stats/getBestTime');
import getNumPlayedGamesRouter = require('./v1/routes/user/stats/getTimesPlayed');
import resetStatsRouter = require('./v1/routes/user/stats/resetStats');



// @ts-ignore
const server = express();

const port = process.env.PORT || 3000;

server.use(expressWinston.logger({
    transports: [
        new winston.transports.Console(),
        new winston.transports.File({ filename: 'combined.log' })
    ],
    format: winston.format.combine(
        winston.format.colorize(),
        winston.format.json()
    ),
    meta: true,
    msg: "HTTP {{req.method}} {{req.url}}",
    expressFormat: true,
    colorize: false,
    ignoreRoute: function (req, res) { return false; }
}));

server.use(express.json());
server.use(express.urlencoded({ extended: true }));

server.use('/api/v1/user/login', loginRouter);
server.use('/api/v1/user/register', registerRouter);
server.use('/api/v1/user/stats/get', getStatsRouter);
server.use('/api/v1/user/stats/put', putStatsRouter);
server.use('/api/v1/user/stats/getbesttime', getBestTimeRouter);
server.use('/api/v1/user/stats/gettimesplayed', getNumPlayedGamesRouter);
server.use('/api/v1/user/stats/reset', resetStatsRouter);


server.listen(port, function () {
    console.log('Server is listening on Port ' + port);
});

