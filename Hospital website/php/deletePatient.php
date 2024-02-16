<?php
include 'connect.php';
$id = $_POST['id'];
$con->query('update room set Patient_ID = null, admission_date = null where Patient_ID = ' . $id);
$con->query('delete from appointment where patient_ID = ' . $id);
$con->query('delete from patient_treatment where patient_ID = ' . $id);
$con->query('delete from relative_phone where relative_ID in (select relative_ID from relative where patient_ID = ' . $id . ")");
$con->query('delete from relative where patient_ID = ' . $id);
$con->query('delete from patient_phone where patient_ID = ' . $id);
$con->query("delete from patient where patient_ID = " . $id);
?>