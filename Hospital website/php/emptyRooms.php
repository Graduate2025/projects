<?php
include 'connect.php';
$r = $con->query('select Room_Num from room where patient_ID is null')->fetchAll();
echo json_encode($r);
?>