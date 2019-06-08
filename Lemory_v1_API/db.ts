import * as mysql from 'mysql';

const connection = mysql.createConnection({
    host     : process.env.DB_HOST,
    user     : process.env.DB_USER,
    password : process.env.DB_PASS,
    database : 'lemory'
});

connection.connect(function(err) {
    if (err) throw err;
});

export = connection;