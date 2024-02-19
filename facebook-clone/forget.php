<?php
include 'connect.php';
$email = $_POST['email'];
$pass = $_POST['pass'];
$email2 = $con->prepare("select Email from user where Email = '$email'");
$email2->execute();
$email2 = $email2->fetchAll(PDO::FETCH_ASSOC);

$passs = password_hash($pass,PASSWORD_DEFAULT);
$uppercase = preg_match('@[A-Z]@', $pass);
$lowercase = preg_match('@[a-z]@', $pass);
$number    = preg_match('@[0-9]@', $pass);
if(count($email2) == 0){
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Email doesn't exist</div>";
    include "forget.html";
}elseif(!$uppercase || !$lowercase || !$number || strlen($pass) < 8) {
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Password must contain at least one uppercase,lowercase,number and be 8 characters or longer</div>";
    include "forget.html";
}else{
    $stmt = $con->prepare('update user set PasswordHash="'.$passs.'" where email = "'.$email.'";');
    $stmt->execute();
include 'index.html';
}
?>