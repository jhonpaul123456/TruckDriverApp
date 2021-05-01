<?php
include "connection.php";

function send_res($id, $name, $mobile, $street, $total, $dates, $status) {
    die(json_encode(array(
        "id"=>$id,
        "name"=>$name,
        "mobile"=>$mobile,
        "street"=>$street,
        "total"=>$total,
        "dates"=>$dates,
        "status"=>$status
    )));
}

$name = $_POST["name"];
$mobile = $_POST["mobile"];
$street = $_POST["street"];
$total = $_POST["total"];
$dates = $_POST["dates"];
$status = $_POST["status"];


$query = "UPDATE `cos_info` SET 
`name`='".mysqli_real_escape_string($conn, $name)."',
`mobile`='".mysqli_real_escape_string($conn, $mobile)."',
`street`='".mysqli_real_escape_string($conn, $street)."',
`total`='".mysqli_real_escape_string($conn, $total)."',
`dates`='".mysqli_real_escape_string($conn, $dates)."',
`status`='".mysqli_real_escape_string($conn, $status)."'
 WHERE id='".$_POST["id"]."'";

$result = mysqli_query($conn, $query);

$query = "SELECT * FROM cos_info WHERE id='".$_POST["id"]."'";

$result = mysqli_query($conn, $query);



if ($result->num_rows > 0) {
    $result = mysqli_fetch_array($result);
    $name = $result["name"];
    $mobile = $result["mobile"];
    $street = $result["street"];
    $total = $result["total"];
    $dates = $result["dates"];
    $status = $result["status"];
 
    send_res("success", $name, $mobile, $street, $total, $dates, $status);
} else {
    send_res("error", null, null, null, null, null, null);
}