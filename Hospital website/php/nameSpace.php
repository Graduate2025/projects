<?php
include 'connect.php';
$r = $con->query('select staff_id from staff')->fetchAll();
echo json_encode($r);
?>