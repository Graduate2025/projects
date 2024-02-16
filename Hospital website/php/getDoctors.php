<?php
include 'connect.php';
$r = $con->query('select * from staff join doctor on staff_id=doctor_id')->fetchAll();
echo json_encode($r);
?>