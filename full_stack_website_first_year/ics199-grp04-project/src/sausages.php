<?php

require_once 'model/db_functions.php';
require_once 'model/db_connect.php';

$products = getSausages();

?>




<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="utf-8" />
	<title>Berryman Brothers Butcheries</title>
</head>

<body>

	<?php require_once 'header.php' ?>
	
	<div class="description">
		
		<?php foreach ($products as $product) { ?>
			<table>
				<tr>
					<td><?= $product['prod_name']; ?></td>
					<td><?= $product['prod_weight']; ?></td>
					<td><?= $product['prod_price']; ?></td>
					<td><?= $product['prod_description']; ?></td>
					<td><?= '<img src="'.$product['prod_image'].'"/>'; ?></td>
					<td><?= $product['cat_name']; ?></td>
				</tr>
			</table>
		<?php } ?>
	</div>

<?php require_once 'footer.php' ?>




</body>
</html>