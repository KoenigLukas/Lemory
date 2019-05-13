const express = require('express');
const logger = require('morgan');

const loginRouter = require('./routes/user/loginUser');
const registerRouter = require('./routes/user/registerUser');
const getStatsRouter = require('./routes/user/stats/getStats');
const putStatsRouter = require('./routes/user/stats/putStats');
const usersRouter = require('./routes/users');

const app = express();

const port = process.env.PORT || 3000;

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));


app.use('/api/users',usersRouter);
app.use('/api/user/login', loginRouter);
app.use('/api/user/register', registerRouter);
app.use('/api/user/stats/get', getStatsRouter);
app.use('/api/user/stats/put', putStatsRouter);

app.listen(port, function () {
    console.log('Example app listening on port ' + port + '!');
});

