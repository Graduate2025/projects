<?php
include 'connect.php';
$email = $_POST['email'];
$pid = $_POST['pid'];
$id = $_POST['id'];
$like = $con->prepare("select Likes from post where PostID = $pid");
$like->execute();
$like = $like->fetchAll(PDO::FETCH_ASSOC)[0]['Likes'] - 1;
$date = $con->prepare("select DateOfCreation from post where PostID = $pid");
$date->execute();
$date = $date->fetchAll(PDO::FETCH_ASSOC)[0]['DateOfCreation'];
$stmt = $con->prepare("update post set Likes = $like where PostID = $pid");
$stmt->execute();
$stmt = $con->prepare("update post set DateOfCreation = '$date' where PostID = $pid");
$stmt->execute();
$stmt = $con->prepare("delete from likes where UserID = $id and PostID = $pid");
$stmt->execute();

echo "
<style>*{display:none}</style>
<form action='home.php' method='post'>
<input type='text' name='email' value='$email'>
<input type='text' name='ref' value='$pid'>
<button id='auto' type='submit'></button>
<script>document.getElementById('auto').click()</script>
</form>
";
?>