<?php
include 'connect.php';
$id = $_POST['id'];
$fid = $_POST['fid'];
$msg = $_POST['msg'];
# to avoid adding single quotes in a single quote we use escape sequence
$msg = str_replace("'","\\'",$msg);

$stmt=$con->prepare("insert into chat(User1,User2,Message)values($id,$fid,'$msg')");
$stmt->execute();

echo "<style>*{display:none}</style>";
echo "<form action='chat.php' method='post'>
<input name='fid' type='text' style='display:none' value='$fid'>
<input name='id' type='text' style='display:none' value='$id'>
<button type='submit' id='auto'></button>
</form>";
echo "<script>document.getElementById('auto').click();</script>";
?>