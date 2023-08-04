var md = require('../model/nhanvien.model');
var multer = require('multer');
const path = require('path');


exports.listuser = async (req, res, next) => {

    try {
        await md.usermodel.find({}).then(user => {
            res.render('list.hbs', {
                viewtitle: 'Danh Sách',
                user: user.map(user => user.toJSON())
            })
        });
    } catch (error) {
        msg = error.message;
    }


};

exports.add = async (req, res, next) => {
       upload(req, res, (err) => {
    console.log(req.body);
    //1. Tạo model

    let user = new md.usermodel({
       hoten : req.body.hoten,
       email : req.body.email,
       matkhau : req.body.matkhau,
       tensan : req.body.tensan,
    
    })
   
    try {
        let new_user =   user.save();
        console.log(new_user);

    } catch (error) {
        console.log(error);
    }


  res.render('add.hbs', { viewtitle: 'thêm thành công' })

})


};


exports.edit = async (req, res, next) => {

    var id = req.params.id;
    // lấy sp cần sửa
    var user = await md.usermodel.findById(id);
   
    if (req.method == 'POST') // viết hoa
    {
      
        console.log(req.body);
        //1. Tạo model
      
        user = await new  md.usermodel();
        user.hoten = req.body.hoten;
        user.email = req.body.email;
        user.hoten = req.body.hoten;
        user.matkhau = req.body.matkhau;
       
        user._id = id; // thêm dòng này để sửa

    
        try {


           await  md.usermodel.findByIdAndUpdate(id, user);

            return res.render('edit.hbs', { viewtitle: "sửa thành công", user: user })
        } catch (error) {
           return console.log(error);
        }
   
    }


    res.render('edit.hbs', { viewtitle: "sửa", user: user })

};


exports.del = async(req, res, next) => {
    let msg = '';
    let id = req.params.id; 
    // lấy sp cần sửa
    let user = await md.usermodel.findById( id);
    if(user == null)
        {
            msg = "không tồn tại sách ";
            res.render('delete.hbs', { msg: msg })
        }

        if(req.method =='POST'){
            try {
                await md.usermodel.findByIdAndDelete(id);
                // chuyển trang
                res.redirect('/api/list');
            } catch (error) {
                msg = error.message;
            }
        }


    res.render('delete.hbs', { msg: msg, user: user })

  
};

exports.profile = async (req, res, next) => {
    let id = req.params.id;
    // lấy sp cần sửa
    let user = await md.usermodel.findById(id);
    res.render('profile.hbs', { viewtitle: "ProFile", user: user })
};