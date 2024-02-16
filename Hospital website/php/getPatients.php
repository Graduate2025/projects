<?php
include 'connect.php';
$r = $con->query('select patient.* ,Room_Num from patient left join room using(patient_ID)')->fetchAll();
echo str_replace("@##@", "'", json_encode($r));
?>