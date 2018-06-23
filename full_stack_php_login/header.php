<header>

    <div class="container" id="header-banner">
    	<div class="row" id="nav-links">
            <h1 class="col-sm-8" id="page-title"><a href="index.php">Manny's Gaming Backlog</a></h1>
            <?php if (isset($_SESSION['username'])) { ?>
                <nav class="col-sm-4" id="nav-bar">

                    <a href="your_account.php">Your account</a>
                    <a href="logout.php">Sign Out</a></button> 
                </nav>
            <?php } ?>
            <?php if (!isset($_SESSION['username'])) { ?>
                <nav class="col-sm-4" id="nav-bar">

                    <a href="login.php">Login</a>
                    <a href="register.php">Register</a>
                </nav>
            <?php } ?>
        </div>
    </div>
</header>
