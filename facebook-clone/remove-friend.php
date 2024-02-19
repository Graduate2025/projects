<?php
    include 'connect.php';
    $id = $_POST['id'];
    $fid = $_POST['idf'];
    $email = $_POST['email'];
    $search = $_POST['search'];
    $stmt = $con->prepare("delete from friend where (UserID1 = $id and UserID2 = $fid) or (UserID2 = $id and UserID1 = $fid)");
    $stmt->execute();
    // echo '<script>window.history.back();</script>';
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