<?php

include "connection.php";

$query = "SELECT * FROM cos_info";
$users = array();

$result = mysqli_query($conn, $query);
if ($result->num_rows > 0) {
    while ($test = mysqli_fetch_array($result)) {
        $user = array(
            "id"=>$test["id"],
            "name"=>$test["name"],
            "mobile"=>$test["mobile"],
            "street"=>$test["street"],
            "total"=>$test["dates"],
            "dates"=>$test["dates"],
            "status"=>$test["status"]    
        );
        array_push($users, $user);
    }
}

echo json_encode(array("users"=>$users));