<?php
include 'connect.php';
$room = $_POST['room'];
$r = $con->query('select staff_id from staff where staff_id not in (select staff_id from staff_room where room_num = "'.$room.'") and staff_type != "Doctor" and department_id = (select department_id from room where room_num = '.$room.')')->fetchAll();
echo json_encode($r);
?>