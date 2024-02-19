<?php
include 'connect.php';
function getMonth(){
    global $month;
    if($month == 'jan'){
        $month = 1;
    }elseif($month == 'feb'){
        $month = 2;
    }elseif($month == 'mar'){
        $month = 3;
    }elseif($month == 'apr'){
        $month = 4  ;
    }elseif($month == 'may'){
        $month = 5;
    }elseif($month == 'jun'){
        $month = 6;
    }elseif($month == 'jul'){
        $month = 7;
    }elseif($month == 'aug'){
        $month = 8;
    }elseif($month == 'sep'){
        $month = 9;
    }elseif($month == 'oct'){
        $month = 10;
    }elseif($month == 'nov'){
        $month = 11;
    }elseif($month == 'dec'){
        $month = 12;
    }
}
$Fname = $_POST['Fname'];
$Lname = $_POST['Lname'];
$email = $_POST['email'];
$email2 = $con->prepare("select Email from user where Email = '$email'");
$email2->execute();
$email2 = $email2->fetchAll(PDO::FETCH_ASSOC);
$phone = $_POST['phone'];
$password = $_POST['pass'];
$pass = password_hash($password,PASSWORD_DEFAULT);
$day = str_pad($_POST['day'],2,'0',STR_PAD_LEFT);
$month = $_POST['month'];
getMonth();
$month = str_pad($month,2,'0',STR_PAD_LEFT);
$year = $_POST['year'];
$gender = $_POST['gender'];
$date = $year."-".$month."-".$day;
// Password constraints
// The requirements:

// Must be a minimum of 8 characters
// Must contain at least 1 number
// Must contain at least one uppercase character
// Must contain at least one lowercase character
$uppercase = preg_match('@[A-Z]@', $password);
$lowercase = preg_match('@[a-z]@', $password);
$number    = preg_match('@[0-9]@', $password);
if(count($email2) != 0){
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Email already exists</div>";
    include "signup.html";
}elseif(!$uppercase || !$lowercase || !$number || strlen($password) < 8) {
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>Password must contain at least one uppercase,lowercase,number and be 8 characters or longer</div>";
    include "signup.html";
}elseif (date("Y") - $year < 18){
    echo "<div style='background-color:#ffeff2;border:1px solid #b76c7d;border-radius:3px;padding:30px;color:#85797d;box-sizing:border-box;width:99%;margin:0 auto'>You must be 18 or older</div>";
    include "signup.html";
}else{
$stmt = $con->prepare('insert into user (FirstName,LastName,Email,PasswordHash,Phone,DOB) values("'.$Fname.'","'.$Lname.'","'.$email.'","'.$pass.'","'.$phone.'","'.$date.'")');
$stmt->execute();
include 'index.html';
}
?>