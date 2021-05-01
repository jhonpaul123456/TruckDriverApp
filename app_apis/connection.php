<?php

$conn = mysqli_connect("localhost", "root", "", "trackerapp_db");

if (!$conn) {
    die("SQl connect err.");
}

// Never ever again add a echo statement in here
/* else {
	echo "success";
}*/