<?php
include 'connect.php';
$num = $_POST['num'];
$id = $_POST['id'];

$con->query('insert into patient_phone(patient_id,phone_number) values('.$id.','.$num.')');
?>