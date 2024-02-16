<?php
include 'connect.php';
$r = $con->query('select Department_id from department')->fetchAll();
echo json_encode($r);
?>