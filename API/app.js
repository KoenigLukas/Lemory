const express = require('express');
const path = require('path');
const logger = require('morgan');
const indexRouter = require('./routes/index');
const loginRouter = require('./routes/user/loginUser');
const registerRouter = require('./routes/user/registerUser');
const getStatsRouter = require('./routes/user/stats/getStats');
const setStatsRouter = require('./routes/user/stats/setStats');
const usersRouter = require('./routes/users');

const app = express();

const port = process.env.PORT || 3000;

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));


app.use('/', indexRouter);
app.use('/users',usersRouter);
app.use('/user/login', loginRouter);
app.use('/user/register', registerRouter);
app.use('/user/stats/get', getStatsRouter);
app.use('/user/stats/set', setStatsRouter);

app.listen(port, function () {
    console.log('Example app listening on port ' + port + '!');
});

module.exports = app;
