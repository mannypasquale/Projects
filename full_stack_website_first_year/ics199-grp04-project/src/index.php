<?php
// Server saves session data for AT LEAST 1 hour
ini_set('session.gc_mxlifetime', 3600);
// client remembers session id for exactly 1 hour
session_set_cookie_params(3600);
session_start();

require_once 'model/db_functions.php';
require_once 'model/db_connect.php';

//Determine what state we are entering the page in
//First visit? Category search?

//print_r($_GET['search']);
//print_r($_GET); //every request for a page is a get request

//first check if adding item to a cart
//ensure all values there

//var_dump($_POST);
if(isset($_POST)){
	if(isset($_POST['prodID'])){
		if(isset($_POST['qty'])){
			//everything is set so add to cart
			$productsToAdd = [
								['productID' => $_POST['prodID'],
								'qty' => $_POST['qty'],
								]
							];
			addProductToDB(session_id(), $productsToAdd);
		}
	}
}

/* //test case for adding multiple items to cart db. uses multi dimensional array.
$testArray = [
	[
		'productID' => 1,
		'qty' => 1,
	],
	[
		'productID' => 2,
		'qty' => 5,
	],
	[
		'productID' => 20,
		'qty' => 50000,
	]
];


print_r($testArray);
addCartToDB(session_id(), $testArray);
*/

if($_GET['meats'] == 'allProducts'){
	$products = getProducts('');
}elseif($_GET['meats'] == 'freshCuts'){
	$products = getProducts('freshCuts');
}elseif($_GET['meats'] == 'sausages'){
	$products = getProducts('sausages');
}else{
	//print_r($_GET['search']);
	$searchItem = $_GET['search'];
	//$_GET['search'];
	$products = getItem($searchItem);
}
?>




<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="utf-8" />
	<title>Berryman Brothers Butcheries</title>
</head>

<body>


	<?php require_once 'header.php' ?>
	<div id="topOfPage"></div>
	


	<div class="description">
		<table>
			<tr>
				<th>Product Name</th>
				<th>Weight</th>
				<th>Price</th>
				<th>Description</th>
				<th>Image</th>
				<th>Category</th>
				<!-- Add to cart button column -->
			</tr>

			<?php 
			foreach ($products as $product) { ?>
					<tr>
						<td><?= $product['prod_name']; ?></td>
						<td><?= $product['prod_weight']; ?></td>
						<td><?= $product['prod_price']; ?></td>
						<td><?= $product['prod_description']; ?></td>
						<td><?= '<img src="'. $product['prod_image'].'"/>'; ?></td>
						<td><?= $product['cat_name']; ?></td>
						<!--  Another alternative, but how to pass multiple values?
						<td><form action="?" method="get"><button name="prodID" type = "submit" value="1">Add to Cart</button></td> 
						-->
						<td class=spinner>
							<form action="index.php" method="POST">
								<input type="number" min="1" max="50" name="qty" value="1"/>
								<!-- Redundant with the type="number"
								<input type="button" value="More" onclick="this.form.qty.value++;"/>
								<input type="button" value="Less" onclick="this.form.qty.value--;"/>
								-->
								<input type="hidden" name="prodID" value="<?= $product['prod_id']; ?>"/>
								<input type="submit" name="submitbutton" value="Add to cart"/>
							</form>
						</td>
					</tr>
			<?php } ?>
			
		</table>
	</div>

<?php require_once 'footer.php' ?>

</body>
</html>