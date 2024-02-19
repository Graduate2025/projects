<?php
include 'connect.php';
$email = $_POST['email'];
$id = $con->prepare("select UserID from user where email = '$email'");
$id->execute();
$id = $id->fetchAll(PDO::FETCH_ASSOC)[0]['UserID'];
$name = $con->prepare("select concat(FirstName,' ',LastName) as name from user where email = '$email'");
$name->execute();
$name = $name->fetchAll(PDO::FETCH_ASSOC);
$name = $name[0]['name'];
$posts = $con->prepare("(select concat(FirstName,' ',LastName) as name,PostID, DateOfCreation, Title from post join userpostrelationship using(PostID) join user using(UserID) where (UserID in(select UserID1 from friend where UserID2 = $id UNION SELECT UserID2 from friend where UserID1 = $id) or UserID = $id) and IsShared = 0 UNION select Name as name,PostID,post.DateOfCreation,Title from post join pagepostrelationship using(postID) join page using(PageID) where PageID in(select Page_id from follows where UserID = $id)) order by Rand()");
$posts->execute();
$posts = $posts->fetchAll(PDO::FETCH_ASSOC);
$shared = $con->prepare("select concat(FirstName,' ',LastName) as name,PostID, DateOfCreation, Title from post join userpostrelationship using(PostID) join user using(UserID) where (UserID in(select UserID1 from friend where UserID2 = $id UNION SELECT UserID2 from friend where UserID1 = $id) or UserID = $id) and IsShared = 1  order by Rand()");
$shared->execute();
$shared = $shared->fetchAll(PDO::FETCH_ASSOC);
$liked = $con->prepare("select PostID from likes where UserID = $id");
$liked->execute();
$liked = $liked->fetchAll(PDO::FETCH_ASSOC);
$friend = $con->prepare("select concat(FirstName,' ',LastName) as 'friend', UserID from friend join user on UserID2 = UserID where UserID1 = $id UNION SELECT concat(FirstName,' ',LastName) as 'friend', UserID from friend join user on UserID1 = UserID where UserID2 = $id");
$friend->execute();
$friend = $friend->fetchAll(PDO::FETCH_ASSOC);
if(!empty($_POST['ref'])){
$ref = $_POST['ref'];
}

function showPost($posts,$shared){
	global $liked;
	global $id;
	global $email;
	if($shared){
		$s = "shared";
	}else{
		$s = '';
	}
	for($i = 0; $i<count($posts);$i++){
		$found = false;
		for($j = 0;$j<count($liked);$j++){
			if($liked[$j]['PostID'] == $posts[$i]['PostID']){
				$found = true;
			}
		}
		if($found == false){
		echo "<div class='post' id='".$posts[$i]['PostID']."'>
		<div class='post-header'>
			<img src='images//profile-picture.jpg' alt='Profile Picture'>
			<div>
			<h2>".$posts[$i]['name']." <p style='color:blue;'>".$s."</p></h2>
			<p>".substr($posts[$i]['DateOfCreation'],0,strlen($posts[$i]['DateOfCreation'])-4)."</p>
		</div>
		</div>
		<div class='post-body'>
			<p>".$posts[$i]['Title']."</p>
		</div>
		<form class='post-footer' action='like.php' method='post'>
			<input type='text' value='$id' name='id' style='display:none'>
			<input type='text' value='$email' name='email' style='display:none'>
			<input type='text' value='".$posts[$i]['PostID']."' name='pid' style='display:none'>
			<button class='like-btn' type='submit'>Like</button>
			</form>
			<form action='share.php' class='post-footer' method='post'>
			<input type = 'text' name='email' value='$email' style='display:none'>
			<input type='text' value='".$posts[$i]['PostID']."' name='pid' style='display:none'>
			<button class='share-btn'>Share</button></form>
			</div>";
		}else{
			echo "<div class='post' id='".$posts[$i]['PostID']."'>
		<div class='post-header'>
			<img src='images//profile-picture.jpg' alt='Profile Picture'>
			<div>
			<h2>".$posts[$i]['name']." <p style='color:blue;'>".$s."</p></h2>
			<p>".substr($posts[$i]['DateOfCreation'],0,strlen($posts[$i]['DateOfCreation'])-4).	"</p>
		</div>
		</div>
		<div class='post-body'>
			<p>".$posts[$i]['Title']."</p>
		</div>
		<form class='post-footer' action='unlike.php' method='post'>
			<input type='text' value='$id' name='id' style='display:none'>
			<input type='text' value='$email' name='email' style='display:none'>
			<input type='text' value='".$posts[$i]['PostID']."' name='pid' style='display:none'>
			<button class='liked' style='background-color:blue; color:white;' type='submit'>Unlike</button>
			</form>
			<form action='share.php' class='post-footer' method='post'>
			<input type = 'text' name='email' value='$email' style='display:none'>
			<input type='text' value='".$posts[$i]['PostID']."' name='pid' style='display:none'>
			<button class='share-btn'>Share</button></form>
			</div>";
		}
	}
}
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Facebook Homepage</title>
		<link rel="stylesheet" type="text/css" href="css//home.css">
		<link
			rel="icon"
			href="https://cdn.iconscout.com/icon/free/png-512/facebook-social-media-fb-logo-square-44659.png"
		/>
	</head>
<body>
	<?php
	if(empty($ref)){

	}else{
		echo "<a id='auto' href='#$ref'></a>
		<script>document.getElementById('auto').click()</script>
		";
	}
	?>
	<!-- Header section -->
	<header>
		<!-- Logo -->
		<img src="images//facebook-logo.png" alt="Facebook">
		<!-- Search bar -->
		<form action='search.php' method="post">
			<input type="text" placeholder="Search Facebook" name="search">
			<?php
			echo "<input type = 'text' value='$id' name='id' style='display:none'>";
			echo "<input type = 'text' value='$email' name='email' style='display:none'>";
			?>
			<button type="submit" class="search" style="background-color: white;border:none; border-radius: 3px; padding:5px 10px;">Search</button>
		</form>
		<!-- Navigation links -->
		<nav>
			<ul>
				<li><form action='log-out.php'><button style='background-color:transparent;border:none;color:white;font-size=50px' type='submit'>Log Out</button></form></li>
				<!-- <li><a href="#">Profile</a></li>
				<li><a href="#">Friends</a></li>
				<li><a href="#">Notifications</a></li>
				<li><a href="#">Settings</a></li> -->
			</ul>
		</nav>
	</header>

	<!-- Main content section -->
	<section class="main-content">
		<!-- Left sidebar -->
		<aside class="left-sidebar">
			<!-- User profile picture and name -->
			<div class="user-profile">
				<img src="images//profile-picture.jpg" alt="Profile Picture">

				<!-- <h3>Ahmed Nasser</h3> -->
				<?php
				echo "<h3>$name</h3>";
				?>
			</div>
			<!-- Friend list -->
			<div class="friend-list">
				<h4>Friends</h4>
				<ul>
					<!-- <li><a href="#">Friend 1</a></li>
					<li><a href="#">Friend 2</a></li>
					<li><a href="#">Friend 3</a></li>
					<li><a href="#">Friend 4</a></li> -->
					<?php
					for($i = 0; $i<count($friend); $i++){
						echo "<li><form action='chat.php' method='post'>
						<input name='fid' style='display:none' value='".$friend[$i]['UserID']."'>
						<input name='id' style='display:none' value='$id'>
						<button style='background-color:transparent;border:none;font-size:20px;cursor:pointer;' type='submit'>".$friend[$i]['friend']."</button>
						</form></li>";
					}
					?>
				</ul>
			</div>
		</aside>
		<!-- News feed -->
		<div class="news-feed">
			<div class="create-post">
				<form action="create-post.php" method="post" enctype="multipart/form-data">
					<?php
					echo "<input type = 'text' name='email' value='$email' style='display:none'>";
					?>
					<textarea name="postText" class="post-textarea" placeholder="What's on your mind?"></textarea>
					<!-- <input type="file" name="postImage" class="post-image-input"> -->
					<button type="submit" class="post-btn">Post</button>
				</form>
			</div>
			<!-- Post 1 -->
			<?php
			showPost($posts,false);
			showPost($shared,true);
			?>
			<!-- <div class="post">
				<div class="post-header">
					<img src="images//profile-picture.jpg" alt="Profile Picture">
					<div>
					<h2>John Doe</h2>
					<p>Posted on May 12, 2023</p>
				</div>
				</div>
				<div class="post-body">
					<p>Here is the text of the post.</p>
					<img src="test" alt="Post Image">
				</div>
				<div class="post-footer">
					<button class="like-btn">Like</button>
					<button class="share-btn">Share</button>
				</div>
				<div class="comments">
					<div class="comments-section">
						<h2>Comments</h2>
						<form>
						  <div class="form-group">
						    <img src="images//profile-picture.jpg" alt="User Profile Picture">
						    <div class="input-group">
							<input type="text" class="comment-input" placeholder="Write a comment...">
							<div class="input-group-append">
							  <button type="submit" class="comment-btn">Comment</button>
							</div>
						    </div>
						  </div>
						</form>
						<ul class="comments-list">
						  <li class="comment">
						    <div class="comment-body">
							<img src="images//profile-picture.jpg" alt="User Profile Picture">
							<div class="comment-content">
							  <div class="comment-header">
							    <h5 class="comment-author">Ahmed</h5>
							    <span class="comment-date">2 hours ago</span>
							  </div>
							  <p class="comment-text">This is a comment.</p>
							</div>
						    </div>
						    <ul class="replies-list">
							<li class="reply">
							  <div class="reply-body">
							    <img src="images//profile-picture.jpg" alt="User Profile Picture">
							    <div class="reply-content">
								<div class="reply-header">
								  <h5 class="reply-author">Hazem</h5>
								  <span class="reply-date">1 hour ago</span>
								</div>
								<p class="reply-text">This is a reply to the comment.</p>
							    </div>
							  </div>
							</li>
						    </ul>
						  </li>
						</ul>
					    </div>
				</div>
			</div> -->

		</div>
	</section>
</body>
</html>