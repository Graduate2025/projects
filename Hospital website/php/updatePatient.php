<?php
include 'connect.php';
$id = $_POST['id'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];
$birth = $_POST['birth'];
$gender = $_POST['gender'];
$disease = $_POST['disease'];
$address = $_POST['address'];
$room = $_POST['room'];

$con->query('update patient set  patient_fname = "' . $fname . '",patient_lname="'.$lname.'" ,date_of_birth = "' . $birth . '" ,gender = "' . $gender . '" ,disease = "' . $disease . '" ,address = "' . $address . '" where patient_id = ' . $id);
$con->query('update room set patient_id = null where patient_id = ' . $id);
$con->query('update room set patient_id = '.$id. ' where room_num = '.$room)
?>