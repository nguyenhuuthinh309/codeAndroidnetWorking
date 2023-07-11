<?php
//Khai bao cac bien
$server="localhost"; $u="id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e"; $p="Thinh2003@@"; $db="id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e";
//tao ket noi voi CSDL
$con=new mysqli($server,$u,$p,$db);
//kiem tra ket noi, neu co loi thi thong bao
if($con->connect_err){
     die("Loi ket noi: ".$con->connect_err);
}
//chuoi insert
$sql="update BangTest SET tensp='update lastname1' WHERE Idsp=12";
//thuc hien insert
if($con->query($sql)===TRUE){
     echo "Update thanh cong";
}
else{
     echo "Loi: ".$con->error;
}
?>