<?php
include 'connect.php';
$r = $con->query('select * from staff')->fetchAll();
echo json_encode($r);
?>