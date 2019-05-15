import * as express from 'express';
import * as sql from '../../db.js';
const router = express.Router();

router.get('/', function(req, res, next) {
    res.send('PLEASE KILL ME NOW ');
});

export = router;