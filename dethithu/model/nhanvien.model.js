var db = require('./db');


var UserSchema = new db.mongoose.Schema({

    hoten:{type: String , required: true},
    email:{type: String , required: true},
    matkhau:{type: String , required: true},
    tensan:{type: String , required: true},
   
  
 
});

let usermodel =  db.mongoose.model('user',UserSchema);
module.exports = {usermodel};