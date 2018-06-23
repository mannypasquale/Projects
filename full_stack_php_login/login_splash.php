<?php 
 session_start();
?>
<?php
include 'models/db_connect.php';
?>

<?php 
if (!isset($_SESSION['username'])) {
    
$result = pg_prepare($db, "login_query", "SELECT users_info.username, users.email, users_info.first_name, users_info.last_name FROM users, users_info WHERE users.email = users_info.email AND users.email = lower($1) AND password = crypt($2, password)");
$result = pg_execute($db, "login_query", array($_POST['email-login'], $_POST['password-login']));

$arr = pg_fetch_all($result);

if (!empty($arr)){
    
    $_SESSION['username'] = 'manny';
    $_SESSION['login-status'] = 'CORRECT CREDENTIALS';
    $_SESSION['user_info'] = $arr;
}
}
if (empty($arr)){
    $_SESSION['login-status'] = 'INCORRECT CREDENTIALS';
}

?>

<!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="mystyle.css">
    <script src="main.js"></script>
</head>
<body>


    <?php require_once 'header.php' ?>




<?php 
    if ($_SESSION['login-status'] == 'INCORRECT CREDENTIALS' ) { ?>
        <div class="container">
        <h1>INCORRECT LOGIN</h1>
        <h3><a href="login.php">Try Again?</a></h3>

    <?php } ?>

<?php if (isset($_SESSION['username'])) { ?>
    <div class="container">
    <h1>Already Logged in</h1>

<?php } ?>
    </body>
    </html>