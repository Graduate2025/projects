<?php
include 'connect.php';
$id = $_POST['id'];

$con->query('delete from staff_room where room_num = '.$id);
$con->query('delete from room where room_num = '.$id);
?>