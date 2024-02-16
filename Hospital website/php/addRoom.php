<?php
include 'connect.php';
$room = $_POST['room'];
$dep = $_POST['dep'];

$con->query('insert into room(room_num,department_id) values('.$room.','.$dep.')');
?>