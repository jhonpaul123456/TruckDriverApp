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

$query = "DELETE FROM truckinfo where id='".$_POST["id"]."'";

$result = mysqli_query($conn, $query);


send_res($result ? "success": "error", null, null, null, null);
