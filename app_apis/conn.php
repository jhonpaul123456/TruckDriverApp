<?php

$conn = mysqli_connect("localhost", "root", "", "trackerapp_db");

if (!$conn) {
    die("SQl connect err.");
}

// Never ever add an echo statement here too.