<?php
include 'connect.php';
$id = $_POST['id'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];

$con->query('insert into patient(patient_id,patient_fname,patient_lname) values('.$id.',"'.$fname.'","'.$lname.'")');
?>