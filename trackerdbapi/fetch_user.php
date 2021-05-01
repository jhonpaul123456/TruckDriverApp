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

$query = "SELECT * FROM truckinfo WHERE id='".$_GET["id"]."'";

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