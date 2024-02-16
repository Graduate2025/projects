<?php
include 'connect.php';

$r = $con->query('select department_id, room_num, d_name, concat(patient_fname," ",patient_lname) as name from (department left join room using(department_id)) left join patient using(patient_id)')->fetchAll();
echo json_encode($r);
?>