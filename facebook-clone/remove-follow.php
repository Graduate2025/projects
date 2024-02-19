<?php
    include 'connect.php';
    $id = $_POST['id'];
    $pid = $_POST['pid'];
    $email = $_POST['email'];
    $search = $_POST['search'];
    $stmt = $con->prepare("delete from follows where UserID = $id and Page_id = $pid");
    $stmt->execute();

    $followers = $con->prepare("select TotalLikes from page where PageID = $pid");
    $followers->execute();
    $followers = $followers->fetchAll(PDO::FETCH_ASSOC)[0]['TotalLikes'];

    $new_followers = $followers - 1;
    $stmt = $con->prepare("update page set TotalLikes = $new_followers where PageID = $pid");
    $stmt->execute();

    echo "
    <style>*{display:none}</style>
    <form action='search.php' method='post'>
    <input type='text' value='$email' name='email'>
    <input type='text' value='$id' name='id'>
    <input type='text' value='$search' name='search'>
    <button id='auto' type='submit'>auto</button>
    </form>
    <script>
    document.getElementById('auto').click();
    </script>
    ";
?>