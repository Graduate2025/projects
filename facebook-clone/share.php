<?php
include 'connect.php';
$email = $_POST['email'];
$pid = $_POST['pid'];
$id = $con->prepare("select UserID from user where Email = '$email'");
$id->execute();
$id = $id->fetchAll(PDO::FETCH_ASSOC);
$id = $id[0]['UserID'];

$stmt = $con->prepare("insert into userpostrelationship(UserID,PostID,IsShared)VALUES($id,$pid,1)");
$stmt->execute();

echo "<form action='home.php' method='post'>
<input type='text' style='display:none' name='email' value='".$email."'>
<button  id='auto' style='display:none'>btn</button>
</form>
<script>document.getElementById('auto').click();
</script>";
?>