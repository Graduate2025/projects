<?php
include 'connect.php';
$email = $_POST['email'];
$pass = $_POST['pass'];

$stmt = $con->prepare('select PasswordHash from user where email = "'.$email.'";');
$stmt->execute();
$Hpass = $stmt->fetchAll(PDO::FETCH_ASSOC);
if(count($Hpass) == 0){
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Email not found</div>";
    include "index.html";
}else{

if(password_verify($pass,$Hpass[0]['PasswordHash'])){
    echo "<form action='home.php' method='post'>
    <input type='text' style='display:none' name='email' value='".$email."'>
    <button  id='auto' style='display:none'>btn</button>
    </form>
    <script>document.getElementById('auto').click();
    </script>";
}
else{
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Wrong password</div>";
    include "index.html";
}
}
?>