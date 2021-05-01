<?php

$conn = mysqli_connect("localhost", "root", "", "truck_db");

if (!$conn) {
    die("SQl connect err.");
}