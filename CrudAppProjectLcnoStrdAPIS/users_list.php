<?php

include "connection.php";

$query = "SELECT * FROM truckinfo";
$users = array();

$result = mysqli_query($conn, $query);
if ($result->num_rows > 0) {
    while ($test = mysqli_fetch_array($result)) {
        $user = array(
            "id"=>$test["id"],
            "driver_name"=>$test["driver_name"],
            "plate_number"=>$test["plate_number"],
            "truck_condition"=>$test["truck_condition"],
            "truck_type"=>$test["truck_type"]
        );
        array_push($users, $user);
    }
}

echo json_encode(array("users"=>$users));