<?php
include 'connect.php';
$fid = $_POST['fid'];
$id = $_POST['id'];
$name = $con->prepare("select concat(FirstName,' ',LastName) as 'name' from user where UserID = $fid");
$name->execute();
$name = $name->fetchAll(PDO::FETCH_ASSOC)[0]['name'];

$email = $con->prepare("select Email from user where UserID = $id");
$email->execute();
$email = $email->fetchAll(PDO::FETCH_ASSOC)[0]['Email'];

$msg = $con->prepare("select * from chat where User1 = $id and User2 = $fid UNION select * from chat where User1 = $fid and User2 = $id ORDER BY Time");
$msg->execute();
$msg = $msg->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/chat.css">
    <title>Chat</title>
</head>
<body>
    <header>
		<!-- Logo -->
		<img src="images//facebook-logo.png" alt="Facebook">
		<!-- Navigation links -->
		<nav>
			<ul>
				<li><form action='home.php' method='post'>
					<?php
					echo "<input type='text' name='email' value='$email'style='display:none'>"
					?>
					<button type='submit' class='home' style="background-color: Transparent;color:white;font-size: 20px;border:none">Home</button>
				</form></li>
				<!-- <li><a href="#">Profile</a></li>
				<li><a href="#">Friends</a></li>
				<li><a href="#">Notifications</a></li>
				<li><a href="#">Settings</a></li> -->
			</ul>
		</nav>
	</header>
    <div class="friend"><p><?php echo"$name"?></p></div>
    <div class="message-section" id='main'>
        <?php
        for($i = 0; $i<count($msg); $i++){
            if($msg[$i]['User1'] == $id){
                echo "<div class='sent'><p>".$msg[$i]['Message']."</p></div>";
            }else{
                echo "<div class='rec'><p>".$msg[$i]['Message']."</p></div>";
            }
        }
        ?>
        <!-- <div class="sent"><p>Hello</p></div>
        <div class="rec"><p>Hello, how are you?</p></div> -->
    </div>
    <script>window.onload=function () {
        var objDiv = document.getElementById("main");
        objDiv.scrollTop = objDiv.scrollHeight;
}</script>
    <div class="send-box">
        <form action="send.php" class='send' method='post'>
            <input type="text" name="msg">
            <input type="text" name="id" value='<?php echo "$id"?>' style='display:none'>
            <input type="text" name="fid" value='<?php echo "$fid"?>' style='display:none'>
            <button type="submit">Send</button>
        </form>
    </div>
</body>
</html>