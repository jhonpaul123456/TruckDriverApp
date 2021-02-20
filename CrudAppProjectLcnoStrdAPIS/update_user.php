<?php
include "connection.php";

function send_res($status, $driver_name, $plate_number, $truck_condition, $truck_type) {
    die(json_encode(array(
        "status"=>$status,
        "driver_name"=>$driver_name,
        "plate_number"=>$plate_number,
        "truck_condition"=>$truck_condition,
        "truck_type"=>$truck_type
    )));
}

$driver_name = $_POST["driver_name"];
$plate_number = $_POST["plate_number"];
$truck_condition = $_POST["truck_condition"];
$truck_type = $_POST["truck_type"];

$query = "UPDATE `truckinfo` SET 
`driver_name`='".mysqli_real_escape_string($conn, $driver_name)."',
`plate_number`='".mysqli_real_escape_string($conn, $plate_number)."',
`truck_condition`='".mysqli_real_escape_string($conn, $truck_condition)."',
`truck_type`='".mysqli_real_escape_string($conn, $truck_type)."'
 WHERE id='".$_POST["id"]."'";

mysqli_query($conn, $query);

$query = "SELECT * FROM truckinfo WHERE id='".$_POST["id"]."'";

$result = mysqli_query($conn, $query);
if ($result->num_rows > 0) {
    $result = mysqli_fetch_array($result);
    $driver_name = $result["driver_name"];
    $plate_number = $result["plate_number"];
    $truck_condition = $result["truck_condition"];
    $truck_type = $result["truck_type"];
    send_res("success", $driver_name, $plate_number, $truck_condition, $truck_type);
} else {
    send_res("error", null, null, null, null);
}

echo json_encode(array("users"=>$users));