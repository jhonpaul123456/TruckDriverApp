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

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $name = $_POST["name"];
    $mobile = $_POST["mobile"];
    $street = $_POST["street"];
    $total = $_POST["total"];
    $dates = $_POST["dates"];
    $status = $_POST["status"];

    $query = "INSERT INTO `cos_info`(`name`, `mobile`, `street`, `total`, `dates`, `status`) VALUES (
        '".mysqli_real_escape_string($conn, $name)."',
        '".mysqli_real_escape_string($conn, $mobile)."',
        '".mysqli_real_escape_string($conn, $street)."',
        '".mysqli_real_escape_string($conn, $total)."',
        '".mysqli_real_escape_string($conn, $dates)."',
        '".mysqli_real_escape_string($conn, $status)."'
    )";

    $result = mysqli_query($conn, $query);

    if ($result) {
        send_res("success", $name, $mobile, $street, $total, $dates, $status);
    } else {
        send_res("error", null, null, null, null, null, null, null);
    }
} 
