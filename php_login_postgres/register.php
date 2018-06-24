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

    <div class="container" id="form-container">
    
        <form action="new_account.php" target="_self" id="sign-up-form" method="post">
            <label for="username">Username:</label>
            <input type="text" placeholder="Username" name="username" required><br>
            <label for="password">Password: </label>
            <input type="password" placeholder="Enter Password" name="password" required><br>
            <labe for="email">Email</labe>
            <input type="email" placeholder="Enter Email" name="email" required><br>
            <label for="firstname">First Name: </label>
            <input type="text" placeholder="First Name" name="firstname" required><br>
            <label for="lastname">Last Name:</label>
            <input type="text" placeholder="Last Name" name="lastname" required><br>

            <button type="submit" value="Submit">Register</button>
            
        </form>

    </div>
    
</body>
</html>