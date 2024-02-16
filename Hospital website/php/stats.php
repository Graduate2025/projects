<?php
include 'connect.php';
$doctors = $con->query('select count(doctor_ID) as doctor from doctor')->fetchAll();
$patients = $con->query('select count(patient_ID) as patient from patient')->fetchAll();
$rooms = $con->query('select count(room_num) as room from room where room_type = "room"')->fetchAll();
$icu = $con->query('select count(room_num) as icu from room where room_type = "ICU"')->fetchAll();
$ot = $con->query('select count(room_num) as ot from room where room_type = "operation theatre"')->fetchAll();
$nurses = $con->query('select count(staff_id) as nurse from staff where staff_type = "Nurse"')->fetchAll();

echo json_encode([$doctors[0],$patients[0],$rooms[0],$icu[0],$ot[0],$nurses[0]])
?>