<%@include file="includes/imports.jsp"%><!DOCTYPE html>
<html lang="it">

<head>
<%@include file="includes/head.jsp"%>
<title>Agave - Shop Item</title>
</head>

<body>
	<f:view>
		<!-- Navigation -->
		<%@include file="includes/header.jsp"%>

		<!-- Page Content -->
		<div class="container">

			<div class="row">
				<div class="col-md-12">

					<div class="thumbnail">
						<img class="img-responsive" src="http://placehold.it/1150x400"
							alt="">

						<div class="caption-full">
							<h4 class="pull-right price">
								<fmt:formatNumber type="currency" currencySymbol=" &euro; "
									value="${productController.product.price}" />
							</h4>
							<h2>${productController.product.name}</h2>
							<div>Codice: ${productController.product.code}</div>
							<div>${productController.product.description}</div>
						</div>

						<div class="ratings">
							<p class="pull-right">3 reviews</p>
							<p>
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> 4.0 stars
							</p>
						</div>
					</div>

					<div class="well">

						<div class="text-right">
							<a class="btn btn-success">Leave a Review</a>
						</div>

						<hr>

						<div class="row">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> Anonymous <span
									class="pull-right">10 days ago</span>
								<p>This product was great in terms of quality. I would
									definitely buy another!</p>
							</div>
						</div>

						<hr>

						<div class="row">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> Anonymous <span
									class="pull-right">12 days ago</span>
								<p>I've alredy ordered another one!</p>
							</div>
						</div>

						<hr>

						<div class="row">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> Anonymous <span
									class="pull-right">15 days ago</span>
								<p>I've seen some better than this, but not at this price. I
									definitely recommend this item.</p>
							</div>
						</div>

					</div>

				</div>
			</div>

		</div>
		<!-- /.container -->

		<!-- Footer -->
		<%@include file="includes/footer.jsp"%>

		<%@include file="includes/boostrapjquery.jsp"%>
	</f:view>
</body>

</html>
