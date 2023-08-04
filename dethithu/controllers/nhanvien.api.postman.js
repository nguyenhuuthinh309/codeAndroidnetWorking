
var md = require('../model/nhanvien.model');

exports.list = async (req, res, next) => {

    try {
        let list = await md.usermodel.find();
        if (list) {
            return res.status(200).json({ data: list, msg: 'lấy thành công' })
        } else {
            return res.status(204).json({ msg: 'không có dữ liệu' });
        }
    } catch (err) {
        return res.status(500).json({ msg: err.message });
    }
}

exports.add = async (req, res, next) => {
    console.log(req.body);
    var user = new md.usermodel({
        hoten: req.body.hoten,
        email: req.body.email,
        matkhau: req.body.matkhau,
        tensan: req.body.tensan,
    })
    // save the user
    await user.save();
    res.json({ success: true, msg: 'Successful created new user.' });
}

exports.edit = async(req, res, next) => {
    var id = req.params.id;
    // lấy sp cần sửa
    var user = await md.usermodel.findById(id);
         console.log(req.body);
        //1. Tạo model
        user = await new  md.usermodel();
        user.email = req.body.email;
        user.hoten = req.body.hoten;
        user.matkhau = req.body.matkhau;
        user.tensan = req.body.tensan;
     
        user._id = id; // thêm dòng này để sửa 
        try {
           await  md.usermodel.findByIdAndUpdate(id, user);
           res.json({ status: 1, msg: ' sửa thành công' });
        } catch (error) {
           return console.log(error);
        }
    ////////////////////

    //1. Tạo model
   
    
}

exports.profile = async(req, res, next) => {
    var id = req.params.id;
    // lấy sp cần sửa
 
   
    try {
        let list = await md.usermodel.findById(id);
        if (list) {
            return res.status(200).json({ data: list, msg: 'lấy thành công' })
        } else {
            return res.status(204).json({ msg: 'không có dữ liệu' });
        }
    } catch (err) {
        return res.status(500).json({ msg: err.message });
    }


}

exports.del = async (req, res, next) => {
    let id = req.params.id; 
    // lấy sp cần sửa
    let user = await md.usermodel.findById(id);
    if(user == null)
        {
            res.json({ status: 1, msg: 'không tìm thấy user xóa' });
            
        }

       
            try {
                await md.usermodel.findByIdAndDelete(id);
                // chuyển trang
                res.json({ status: 1, msg: 'xóa thành công' });
            } catch (error) {
              console.log(error);
            }
      
}