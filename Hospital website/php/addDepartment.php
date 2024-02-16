<?php
include 'connect.php';
$name = $_POST['name'];
$dep = $_POST['dep'];

$con->query('insert into department(d_name,department_id) values("'.$name.'",'.$dep.')');
?>