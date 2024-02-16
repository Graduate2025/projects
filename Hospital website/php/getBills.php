<?php
include 'connect.php';
$id = $_POST['id'];

$r = $con->query('select sum((treatment_end_date - treatment_start_date)*treatment_price) as bill from patient_treatment join treatment using(treatment_code) where patient_id = '.$id.' GROUP by(patient_id)')->fetchAll();
echo json_encode($r);
?>