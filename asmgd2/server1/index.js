var express = require('express');
var app = express();
app.use(express.json());

const path = require('path');
const hbs = require('hbs');
const http = require("http")

const templatePath = path.join(__dirname,'./tempelates');
app.use(express.json());
app.set('view engine','hbs');
app.set('views',templatePath);
app.use(express.urlencoded({ extended: true }));

// doc dữ liệu từ cơ sở dữ liệu 
var mysql = require('mysql2');
const { emit } = require('process');
var con = mysql.createConnection({
    host: 'sql.freedb.tech',
    user:"freedb_ph23860",
    password: 'mt4X458A?8b!wV#',
    database:'freedb_ph23860'
});
/////////////////////////////////// SERVER 

app.get('/', (req, res) => {
    res.render('AddorEdit')
});
app.post('/add', (req, res) => {

   
    id = req.body.id;
    email = req.body.email;
    mk = req.body.mk;
    tensan = req.body.tensan;
     datainsert=[id, email, mk, tensan]
   
   con.connect((err)=>{
       if(err) throw err;
       con.query("insert into UserAmin (id,email,mk,tensan) values (?,?,?,?)",datainsert,(err,result,fields)=>{
           if(err)
           throw err;
           kq = JSON.stringify(result);
           res.redirect("/list");
       });
   }) 


})
app.post('/update', (req, res) => {
    id = req.body.id;
  email = req.body.email;
    mk = req.body.mk;
    tensan = req.body.tensan;
     const data=[email,mk,tensan,id];
     
    con.query("UPDATE UserAmin SET email=?, mk=?, tensan=? WHERE id =?",data,(err,result,fields)=>{
        res.redirect("/list");
    
    });

})





app.get('/list', (req, res) => {

    con.connect((err)=>{
        if(err) throw err;
        con.query("SELECT * FROM UserAmin",(err,result,fields)=>{
            if(err) throw err;
            kq = JSON.stringify(result);
            res.render('view_taikhoan',{

                taikhoan:result.map(kq => kq)
            })
          

        });
    })
   
   
});


app.get("/edit/:id", (req, res) => {
    con.connect((err)=>{
        if(err) throw err;
    con.query("select * from UserAmin WHERE id ="+req.params.id,(err,result,fields)=>{
        kq1 = JSON.stringify(result);
        if(!req.params.id) res.status(404).send("không tìm thấy item sửa");
       
        else{
          
            res.render('Edit',{
             

                title1: 'Update_'+req.params.id,
             
             

              })
        }
       });
    });
})


app.get("/del/:id", (req, res) => {
  
 
       con.query("DELETE FROM UserAmin WHERE id ="+req.params.id,(err,result,fields)=>{
        if(!req.params.id) res.status(404).send("không tìm thấy item xóa");
        else{
            res.redirect("/list");
        }
       });
 
});

   
    

/////////////////////////////////  API  

app.get('/user/select', function(req, res){
    con.connect((err)=>{
        if(err) throw err;
        con.query("SELECT * FROM UserAmin",(err,result,fields)=>{
            if(err)
            throw err;
            kq = JSON.stringify(result);
            console.log(kq);
            res.send(kq);
        });
    })
   
    });
    app.post('/user/insert', function(req, res){
        const id = req.body.id;
        const email = req.body.email;
        const mk = req.body.mk;
        const tensan = req.body.tensan;
         const data=[id, email, mk, tensan]
        con.connect((err)=>{
            if(err) throw err;
            con.query("insert into UserAmin (id,email,mk,tensan) values (?,?,?,?)",data,(err,result,fields)=>{
                if(err)
                throw err;
                kq = JSON.stringify(result);
                console.log(kq);
                res.send(kq);
            });
        })
       
        });




        app.get('/product/select', function(req, res){
            con.connect((err)=>{
                if(err) throw err;
                con.query("SELECT * FROM HoaDon",(err,result,fields)=>{
                    if(err)
                    throw err;
                    kq = JSON.stringify(result);
                    console.log(kq);
                    res.send(kq);
                });
            })
           
            });
            app.post('/product/insert', function(req, res){
                const id = req.body.id;
                const tenkhach = req.body.tenkhach;
                const sdt = req.body.sdt;
                const ngaythue = req.body.ngaythue;
                const khunggio = req.body.khunggio;
                const tongtien = req.body.tongtien;
                const trangthai = req.body.trangthai;
                 const data=[id, tenkhach, sdt, ngaythue,khunggio,tongtien,trangthai];
                con.connect((err)=>{
                    if(err) throw err;
                    con.query("insert into HoaDon (id,tenkhach,sdt,ngaythue,khunggio,tongtien,trangthai) values (?,?,?,?,?,?,?)",data,(err,result,fields)=>{
                        if(err)
                        throw err;
                        kq = JSON.stringify(result);
                        console.log(kq);
                        res.send(kq);
                    });
                })
               
                });
    /////////////////////////////////////////
var server = app.listen(3000,function(){
    var host = server.address().address;
    var port = server.address().port;
    console.log("server listening : " + port,host);
});