var express = require('express');
var app = express();
var kq = "";


// doc dữ liệu từ cơ sở dữ liệu 
var mysql = require('mysql');
var con = mysql.createConnection({
    host: 'sql.freedb.tech',
    user:"freedb_ph23860",
    password: 'mt4X458A?8b!wV#',
    database:'freedb_ph23860'
});
con.connect((err)=>{
    if(err)
    throw err;
    con.query("SELECT * FROM BangTest",(err,result,fields)=>{
        if(err)
        throw err;
        kq = JSON.stringify(result);
        console.log(kq);
    });
})
app.get('/', function(req, res){
    res.send(kq);
    });
    
var server = app.listen(3000,function(){
    var host = server.address().address;
    var port = server.address().port;
    console.log("server listening : " + port,host);
});