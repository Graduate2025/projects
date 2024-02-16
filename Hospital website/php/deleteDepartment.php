<?php
include 'connect.php';
$id = $_POST['id'];

$con->query('delete from appointment where doctor_id in (select staff_id from staff where department_id = '.$id.')');
$con->query('delete from doctor where doctor_id in (select staff_id from staff where department_id= '.$id.')');
$con->query('delete from staff_room where room_num in (select room_num from room where department_id= '.$id.')');
$con->query('delete from room where department_id= '.$id);
$con->query('delete from staff where department_id= '.$id);
$con->query('delete from department where department_id= '.$id);
?>