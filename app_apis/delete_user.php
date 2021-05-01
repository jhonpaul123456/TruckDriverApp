<?php
include "connection.php";

function send_res($id, $name, $mobile, $street, $total) {
    die(json_encode(array(
        "id"=>$id,
        "name"=>$name,
        "mobile"=>$mobile,
        "street"=>$street,
        "total"=>$total
    )));
}

$query = "DELETE FROM cos_info where id='".$_POST["id"]."'";

$result = mysqli_query($conn, $query);


send_res($result ? "success": "error", null, null, null, null,null);
