<?php
include 'connect.php';
$email = $_POST['email'];
$text = str_replace("'","\\'",$_POST['postText']);
$id = $con->prepare("select UserID from user where Email = '$email'");
$id->execute();
$id = $id->fetchAll(PDO::FETCH_ASSOC);
$id = $id[0]['UserID'];

$stmt = $con->prepare("insert into post(Title)VALUES('$text')");
$stmt->execute();

$PostId = $con->prepare("select PostID from post where Title = '$text' order by DateOfCreation desc limit 1");
$PostId->execute();
$PostId = $PostId->fetchAll(PDO::FETCH_ASSOC);
$PostId = $PostId[0]['PostID'];

$stmt = $con->prepare("insert into userpostrelationship(UserID,PostID,IsShared)values($id,$PostId,0)");
$stmt->execute();

echo "<form action='home.php' method='post'>
<input type='text' style='display:none' name='email' value='".$email."'>
<button  id='auto' style='display:none'>btn</button>
</form>
<script>document.getElementById('auto').click();
</script>";
?>