<?php
session_start();

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

<form id="login-form" action="login_splash.php" method="post">
    <label for="email">Email: </label>
    <input type="text" name="email-login" placeholder="Enter Email" required>
    <lable for="password-login">Password</lable>
    <input type="password" name="password-login" placeholder="Enter password" required>

    <button type="submit" value="Submit">Login</button>

</form>
    
</body>
</html>