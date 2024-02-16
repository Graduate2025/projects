<?php
include 'connect.php';
$id = $_POST['id'];
$phone = $_POST['phone'];
$address = $_POST['address'];
$salary = $_POST['salary'];
$spec = $_POST['spec'];
$department = $_POST['department'];

$con->query('update staff set Staff_address = "' . $address . '", Staff_Phone = "' . $phone . '", salary=' . $salary . ', Department_id = ' . $department . ' where Staff_id = ' . $id);
$con->query('update doctor set doctor_specialization = "' . $spec . '" where doctor_id = ' . $id);
?>