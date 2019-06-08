import express = require('express');
import logger = require("morgan");
import * as dotenv from "dotenv";
dotenv.config();

import registerRouter = require('./routes/user/registerUser');
import loginRouter = require('./routes/user/loginUser');
// import getStatsRouter = require('./routes/user/stats/getStats');
import putStatsRouter = require('./routes/user/stats/putStats');
import usersRouter = require('./routes/users');


// @ts-ignore
const server = express();

const port = process.env.PORT || 3000;

server.use(logger('dev'));
server.use(express.json());
server.use(express.urlencoded({ extended: true }));


server.use('/api/users',usersRouter);
server.use('/api/user/login', loginRouter);
server.use('/api/user/register', registerRouter);
// server.use('/api/user/stats/get', getStatsRouter);
server.use('/api/user/stats/put', putStatsRouter);

server.listen(port, function () {
    console.log('Example server listening on port ' + port + '!');
});

