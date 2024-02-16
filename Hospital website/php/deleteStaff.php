<?php
include 'connect.php';
$id = $_POST['id'];
$con->query('delete from staff_room where staff_id = '.$id);
$con->query('delete from doctor where doctor_id = '.$id);
$con->query('delete from staff where staff_id = '.$id);
?>