<?php
include 'connect.php';
$idf = $_POST['idf'];
$id = $_POST['id'];
$email = $_POST['email'];
$search = $_POST['search'];
$stmt = $con->prepare("insert into friend(UserID1,UserID2) VALUES($id,$idf)");
$stmt->execute();
echo "
<style>*{display:none}</style>
<form action='search.php' method='post'>
    <input type='text' value='$email' name='email'>
    <input type='text' value='$id' name='id'>
    <input type='text' value='$search' name='search'>
    <button id='auto' type='submit'></button>
    </form>
    <script>
    document.getElementById('auto').click();
    </script>
    ";
?>