<?php
$email = $_POST['email'];
$search = trim($_POST['search']);
$search = str_replace("'","\\'",$search);
if($search==''){
	echo "<form action='home.php' method='post'><input type='text' name='email' value='$email' style='display:none'><button id='auto' type='submit' style='display:none'></button>";
	echo "<script>document.getElementById('auto').click();</script>";
}
$id = $_POST['id'];
include 'connect.php';

$pages = $con->prepare("select Name,PageID from page where Name like '%$search%'");
$pages->execute();
$pages = $pages->fetchAll(PDO::FETCH_ASSOC);

$follow = $con->prepare("select Name,PageID from page join follows on Page_id = PageID where UserID = $id");
$follow->execute();
$follow = $follow->fetchAll(PDO::FETCH_ASSOC);

$users = $con->prepare("select concat(FirstName,' ',LastName) as 'Name',UserID from user where concat(FirstName,' ', LastName) like '%$search%' and UserID not in ($id)");
$users->execute();
$users = $users->fetchAll(PDO::FETCH_ASSOC);

$friend = $con->prepare("select UserID2 as 'friend' from friend where UserID1 = $id UNION SELECT userID1 as 'friend' from friend where UserID2 = $id");
$friend->execute();
$friend = $friend->fetchAll(PDO::FETCH_ASSOC);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css\search.css">
    <title>search</title>
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
    <!-- <form class='result'>
        <h2>Name</h2>
        <button>button</button>
		</form> -->
	<?php
	for($i = 0; $i<count($pages); $i++){
		$found = false;
		for($j = 0; $j<count($follow); $j++){
			if($follow[$j]['PageID'] == $pages[$i]['PageID']){
				$found = true;
				break;
			}
		}
		if($found == false){
			echo "<form class='result' action='follow.php' method='post'>
			<h2>".$pages[$i]['Name']."</h2>
			<input value='".$pages[$i]['PageID']."' name='pid' style='display:none'>
			<input value='$id' name='id' style='display:none'>
			<input value='$email' name='email' style='display:none'>
			<input value='$search' name='search' style='display:none'>
			<button type='submit'>Follow</button>
			</form>";
		}else{
			echo "<form class='result' action='remove-follow.php' method='post'>
			<h2>".$pages[$i]['Name']."</h2>
			<input value='".$pages[$i]['PageID']."' name='pid' style='display:none'>
			<input value='$id' name='id' style='display:none'>
			<input value='$email' name='email' style='display:none'>
			<input value='$search' name='search' style='display:none'>
			<button type='submit'>unfollow</button>
			</form>";
		}
	}
	for($i = 0; $i<count($users); $i++){
		$found = false;
		for($j = 0; $j<count($friend); $j++){
			if($friend[$j]['friend'] == $users[$i]['UserID']){
				$found = true;
				break;
			}
		}
		if($found == false){
			echo "<form class='result' action='friend.php' method='post'>
			<h2>".$users[$i]['Name']."</h2>
			<input value='".$users[$i]['UserID']."' name='idf' style='display:none'>
			<input value='$id' name='id' style='display:none'>
			<input value='$email' name='email' style='display:none'>
			<input value='$search' name='search' style='display:none'>
			<button type='submit'>Add friend</button>
			</form>";
		}else{
			echo "<form class='result' action='remove-friend.php' method='post'>
			<h2>".$users[$i]['Name']."</h2>
			<input value='".$users[$i]['UserID']."' name='idf' style='display:none'>
			<input value='$id' name='id' style='display:none'>
			<input value='$email' name='email' style='display:none'>
			<input value='$search' name='search' style='display:none'>
			<button type='submit'>Remove friend</button>
			</form>";
		}
	}
	?>
</body>
</html>