<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

$servername = "localhost";
$username = "id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e";
$password = "Thinh3092003@";
$dbname = "id20809291_wp_9c3998c43cd59f4f0dfcf6f72569464e";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}


// check for required fields
if (isset($_POST['tensp']) && isset($_POST['giasp']) && isset($_POST['soluong'])) {

    $tensp = $_POST['tensp'];
    $giasp = $_POST['giasp'];
    $soluong = $_POST['soluong'];

    // include db connect class
    //require_once __DIR__ . '/db_connect.php';

    // connecting to db
    //$db = new DB_CONNECT();

    $sql = "INSERT INTO BangTest(tensp,giasp,soluong) VALUES('$tensp', '$giasp', '$soluong')";

if ($conn->query($sql) === TRUE) {
    $response["success"] = 1;
    $response["message"] = "Product successfully created.";
    // echoing JSON response
    echo json_encode($response);
    //echo "New record created successfully";
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
  //echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();


} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
