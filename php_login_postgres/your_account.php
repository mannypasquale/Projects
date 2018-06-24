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

<?php if (isset($_SESSION['username'])) { ?>
    
    <div class="container">
        <h1>WELCOME BACK <?= $_SESSION['user_info'][0]['first_name'];?></h1>
        <table>
           
                <tr>
                    <td>EMAIL</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                </tr>
                <tr>
                    <td><?= $_SESSION['user_info'][0]['email']; ?></td>
                    <td><?= $_SESSION['user_info'][0]['first_name']; ?></td>
                    <td><?= $_SESSION['user_info'][0]['last_name'];; ?></td>
                </tr>
        </table>

    </div>
<?php } ?>