<?php
include 'connect.php';
$id = $_POST['id'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];
$phone = $_POST['phone'];
$salary = $_POST['salary'];
$address = $_POST['address'];
$department = $_POST['department'];

$con->query('update staff set staff_fname = "'.$fname.'", staff_lname = "'.$lname.'", staff_phone = "'.$phone.'", salary = '.$salary.', staff_address = "'.$address.'", department_id = '.$department.' where staff_id = '.$id)
?>