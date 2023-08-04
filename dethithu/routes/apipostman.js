var express = require('express');
var router = express.Router();
var User = require('../controllers/nhanvien.api.postman')

router.get('/list',User.list);

router.post('/add',User.add);

router.post('/edit/:id',User.edit);

router.post('/delete/:id',User.del);

router.get('/profile/:id',User.profile);

module.exports = router;