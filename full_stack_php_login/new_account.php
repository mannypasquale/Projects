
<?php
session_start();
include 'models/db_connect.php';
?>

<?php

   //$result = pg_prepare($db, "my_query", "INSERT INTO users VALUES ('$_POST[email]', '$_POST[password]')"
//    $q2 = "INSERT INTO users (email, password) VALUES ('$_POST[email]', '$_POST[password]')";
//    $r2 = pg_query($q2);
//     $query = "INSERT INTO test VALUES ('$_POST[username]', '$_POST[password]', '$_POST[email]', '$_POST[firstname]', '$_POST[lastname]')";
//     $result = pg_query($query);

    $result = pg_prepare($db, "my_query", "INSERT INTO users (email, password) VALUES ($1, crypt($2, gen_salt('bf')))");

    $result = pg_execute($db, "my_query", array($_POST['email'], $_POST['password']));

    $r2 = pg_prepare($db, "second_query", "INSERT INTO users_info (email, first_name, last_name, username) VALUES ($1, $2, $3, $4)");
    $r2 = pg_execute($db, "second_query", array($_POST['email'], $_POST['firstname'], $_POST['lastname'], $_POST['username']));
    //$result = pg_prepare($db, "my_query", "INSERT INTO users VALUES (users_user_id_seq.NEXTVAL, '$1', crypt('$2', gen_salt('bf')))");
 
    // function createUser($username, $password, $email, $firstName, $lastName){

    //     $sql = "INSERT INTO users(email, password) VALUES (:email, crypt(':password, gen_salt('bf'))";
    //     $stmt = $this->pdo->prepare($sql);

    //     $stmt->bindValue(':email', $email);
    //     $stmt->bindValue(':password', $password);

    //     $stmt->execute();
    //     //insert into users (email, password) VALUES (
    //         //'email here',
    //         //crypt('johnspassword', gen_salt('bf'))
    //     //);

	
    // }
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

    <div class="container">
        <h1>ACCOUNT CREATED</h1>
        <h3><a href="login.php">Login Now</a></h3>
    </div>
    </body>
    </html>