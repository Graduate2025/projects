<?php
include 'connect.php';
$room = $_POST['room'];

$r = $con->query('select concat(staff_fname," ",staff_lname) as name ,staff_id from staff_room join staff using(staff_id) where room_num = '.$room)->fetchAll();
echo json_encode($r);

?>