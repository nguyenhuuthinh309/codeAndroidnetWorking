var express = require('express');
var router = express.Router();
var User = require('../controllers/nhanvien');


router.get('/list',User.listuser);

router.get('/add', (req, res) => { res.render('add.hbs',{viewtitle: "Thêm Nhân Viênn"}) });
router.post('/add',User.add);

router.get('/edit/:id', User.edit );
router.post('/edit/:id', User.edit );

router.get('/delete/:id', User.del );
router.post('/delete/:id', User.del );


router.get('/profile/:id',User.profile);

module.exports = router;