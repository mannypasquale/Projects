<?php
session_start();
require_once 'model/db_functions.php';
require_once 'model/db_connect.php';

//var_dump($_POST);
if(isset($_POST)){
	if(isset($_POST['prodID'])){
		if(isset($_POST['qty'])){
			//everything is set so add to cart
			$productsToRemove = [
								['productID' => $_POST['prodID'],
								'qty' => $_POST['qty'],
								]
							];
			removeProductFromDB(session_id(), $productsToRemove);
		}
	} else if(isset($_GET['type'])){
		if($_GET['type'] == "clearCart"){
			clearCart(session_id());
		}
	}
}

$products = getUserCart(session_id());
//var_dump($products);
?>




<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="utf-8" />
	<title>Berryman Brothers Butcheries</title>

	<style>

	#subTotal{
		text-align: right;
	}

	</style>
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
				<th>Image</th>
				<th>Qty</th>
				
				<th>Total</th>
				

				<!-- Add to cart button column -->
			</tr>
	
		<?php 
			foreach ($products as $product) { ?>
				<?php $pricePerItem = floatval(ltrim($product['prod_price'], '$')) * $product['prod_qty']; ?>

					<tr>
						<td><?= $product['prod_name']; ?></td>
						<td><?= $product['prod_weight']; ?></td>
						<td><?= $product['prod_price']; ?></td>
						<td><?= '<img src="'. $product['prod_image'].'"/>'; ?></td>
						<td><?= $product['prod_qty']; ?></td>
						<td><?= '$' . number_format($pricePerItem, 2);?></td>
						<td>		
							<form action="shopping_cart.php" method="POST">
								<input type="number" min="1" max="<?= $product['prod_qty']; ?>" name="qty" value="1"/>
								<!-- Redundant with the type="number"
								<input type="button" value="More" onclick="this.form.qty.value++;"/>
								<input type="button" value="Less" onclick="this.form.qty.value--;"/>
								-->
								<input type="hidden" name="prodID" value="<?= $product['prod_id']; ?>"/>
								<input type="submit" name="submitbutton" value="Remove from cart"/>
							</form>
						</td>
						


					</tr>
			<?php } ?>
			
				
		</table>
		<p id="subTotal">
				<?php 
					$cartTotal = 0;
					foreach($products as $product) { ?>
						<?php $pricePerItem2 = floatval(ltrim($product['prod_price'], '$')) * $product['prod_qty'];
							$cartTotal += $pricePerItem2; ?>
					<?php } ?>
				<?= 'Sub-Total ' . '$' . number_format($cartTotal, 2); ?>
				<br>
			
		</p>
					





		<a href="?type=clearCart">Clear cart contents</a>
	</div>

<?php require_once 'footer.php' ?>



</body>
</html>