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

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $driver_name = $_POST["driver_name"];
    $plate_number = $_POST["plate_number"];
    $truck_condition = $_POST["truck_condition"];
    $truck_type = $_POST["truck_type"];
  
    $query = "INSERT INTO `truckinfo`(`driver_name`, `plate_number`, `truck_condition`, `truck_type`) VALUES (
        '".mysqli_real_escape_string($conn, $driver_name)."',
        '".mysqli_real_escape_string($conn, $plate_number)."',
        '".mysqli_real_escape_string($conn, $truck_condition)."',
        '".mysqli_real_escape_string($conn, $truck_type)."'
    )";

    $result = mysqli_query($conn, $query);

    if ($result) {
        send_res("success", $driver_name, $plate_number, $truck_condition, $truck_type);
    } else {
        send_res("error", null, null, null, null);
    }


}