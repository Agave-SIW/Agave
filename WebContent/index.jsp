<%@include file="includes/imports.jsp"%><!DOCTYPE html>
<html lang="it">


<head>
<%@include file="includes/head.jsp"%>
<title>Agave Shop</title>
</head>

<body>

	<f:view>

		<!-- Navigation -->
		<%@include file="includes/header.jsp"%>

		<!-- Page Content -->
		<div class="container">

			<!-- Jumbotron Header -->
			<header class="jumbotron hero-spacer">
				<h1>A Warm Welcome!</h1>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero
					aliquid similique quaerat nam nobis illo aspernatur vitae fugiat
					numquam repellat.</p>
				<p>
					<h:form>
						<h:commandLink action="#{productController.listProducts}"
							value="View all Products" styleClass="btn btn-primary btn-large" />
					</h:form>

				</p>
			</header>

			<hr>

			<!-- Title -->
			<div class="row">
				<div class="col-lg-12">
					<h3>Latest Features</h3>
				</div>
			</div>
			<!-- /.row -->

			<!-- Page Features -->
			<div class="row text-center">

				<c:forEach var="product"
					items="#{productController.getListProducts()}" end="3">
					<h:form>
						<div class="col-md-3 col-sm-6 hero-feature">
							<div class="thumbnail">
								<img src="http://placehold.it/800x500" alt="">
								<div class="caption">
									<h3>${product.name}</h3>
									<p>${product.description}</p>
									<p>
										<a href="#" class="btn btn-primary">Buy Now!</a>
										<h:commandLink action="#{productController.findProduct}"
											value="More Info" styleClass="btn btn-default">
											<f:param name="id" value="#{product.id}" />
										</h:commandLink>
									</p>
								</div>
							</div>
						</div>
					</h:form>
				</c:forEach>

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

		<!-- Footer -->
		<%@include file="includes/footer.jsp"%>


		<%@include file="includes/boostrapjquery.jsp"%>

	</f:view>

</body>

</html>
