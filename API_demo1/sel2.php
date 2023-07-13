<?php
header('Access-Control-Allow-Origin: *');
//Khai bao cac bien
$server="localhost"; $u="id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e"; $p="Thinh2003@@"; $db="id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e";
//tao ket noi voi CSDL
$con=new mysqli($server,$u,$p,$db);
//kiem tra ket noi, neu co loi thi thong bao
if($con->connect_err){
     die("Loi ket noi: ".$con->connect_err);
}
//chuoi insert
$sql="SELECT * from  BangTest";
//thuc hien insert
$result=$con->query($sql);
     while($row[]=$result->fetch_assoc()){//doc tung dong du lieu
         $json=json_encode($row);//chuyen sang json
     }
     echo '{"BangTest":'.$json.'}';
$con->close();
?>