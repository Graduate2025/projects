<?php
include 'connect.php';
$r = $con->query('select patient_id from patient')->fetchAll();
echo json_encode($r);
?>