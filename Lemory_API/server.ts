import express = require('express');
import logger = require("morgan");
import * as dotenv from "dotenv";
dotenv.config();

import v1registerRouter = require('./v1/routes/user/registerUser');
import v1loginRouter = require('./v1/routes/user/loginUser');
import v1getStatsRouter = require('./v1/routes/user/stats/getStats');
import v1putStatsRouter = require('./v1/routes/user/stats/putStats');
import v1getBestTimeRouter = require('./v1/routes/user/stats/getBestTime');
import v1getNumPlayedGamesRouter = require('./v1/routes/user/stats/getTimesPlayed');





// @ts-ignore
const server = express();

const port = process.env.PORT || 3000;

server.use(logger('dev'));
server.use(express.json());
server.use(express.urlencoded({ extended: true }));

server.use('/api/v1/user/login', v1loginRouter);
server.use('/api/v1/user/register', v1registerRouter);
server.use('/api/v1/user/stats/get', v1getStatsRouter);
server.use('/api/v1/user/stats/put', v1putStatsRouter);
server.use('/api/v1/user/stats/getbesttime', v1getBestTimeRouter);
server.use('/api/v1/user/stats/gettimesplayed', v1getNumPlayedGamesRouter);




server.listen(port, function () {
    console.log('Example server listening on port ' + port + '!');
});

