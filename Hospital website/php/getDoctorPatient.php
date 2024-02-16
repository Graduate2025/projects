<?php
include 'connect.php';
$doctor = $_POST['doctor'];
$r = $con->query('select distinct patient_fname, patient_lname from patient join appointment using(patient_id) where doctor_id =' . $doctor)->fetchAll();
echo json_encode($r);
?>