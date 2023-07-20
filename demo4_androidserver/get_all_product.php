<?php

/*
 * Following code will list all the products
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

$result = $conn->query("SELECT *FROM BangTest");

if ($result->num_rows > 0) {
    // looping through all results
    // products node
    $response["BangTest"] = array();
    while($row = $result->fetch_assoc()) {
    //while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["Idsp"] = $row["Idsp"];
        $product["tensp"] = $row["tensp"];
        $product["giasp"] = $row["giasp"];
        $product["soluong"] = $row["soluong"];
        $product["created_at"] = $row["created_at"];
        $product["updated_at"] = $row["updated_at"];

        // push single product into final response array
        array_push($response["BangTest"], $product);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no BangTest found
    $response["success"] = 0;
    $response["message"] = "No BangTest found";

    // echo no users JSON
    echo json_encode($response);
}
?>
