<?php
include 'connect.php';
$id = $_POST['id'];
$r = $con->query('select phone_number from patient_phone where patient_id = '.$id)->fetchAll();
echo json_encode($r);
?>