<?php


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
if (isset($_POST['Idsp']) && isset($_POST['tensp']) && isset($_POST['giasp']) && isset($_POST['soluong'])) {

    $Idsp = $_POST['Idsp'];
    $tensp = $_POST['tensp'];
    $giasp = $_POST['giasp'];
    $soluong = $_POST['soluong'];

    
    $sql = "UPDATE BangTest SET tensp = '$tensp', giasp = '$giasp', soluong = '$soluong' WHERE Idsp = $Idsp";

    if ($conn->query($sql) === TRUE) {
      //echo "Record updated successfully";
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
      echo "Error updating record: " . $conn->error;
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
