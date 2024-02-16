<?php
include 'connect.php';
$num = $_POST['num'];
$id = $_POST['id'];

$con->query('delete from patient_phone where patient_id = '.$id.' and phone_number = '.$num);
?>