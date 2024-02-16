<?php
include 'connect.php';
$id = $_POST['id'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];
$role = $_POST['role'];

$con->query('insert into staff(staff_id,staff_fname,staff_lname,staff_type) values('.$id.',"'.$fname.'","'.$lname.'","'.$role.'")');
if($role=="Doctor"){
    $con->query('insert into doctor(doctor_id) values('.$id.')');
}
?>