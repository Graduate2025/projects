<?php
include 'connect.php';
$staff = explode(",",$_POST['staff']);
$room = $_POST['room'];
$con->query('delete from staff_room where room_num = '.$room);
if($staff[0]!=''){
foreach ($staff as $s){
    $con->query('insert into staff_room(staff_id,room_num) values ('.$s.','.$room.')');
}
}
?>