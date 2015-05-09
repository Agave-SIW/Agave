<%@include file="includes/imports.jsp" %><!DOCTYPE html>
<html lang="it">


<head>
<%@include file="includes/head.jsp" %>
<title>Agave Shop</title>
</head>

<body>

<f:view>

	<!-- Navigation -->
	<%@include file="includes/header.jsp" %>

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

				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<img src="http://placehold.it/800x500" alt="">
						<div class="caption">
							<h3>Feature Label</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
							<p>
								<a href="#" class="btn btn-primary">Buy Now!</a> <a href="#"
									class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<img src="http://placehold.it/800x500" alt="">
						<div class="caption">
							<h3>Feature Label</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
							<p>
								<a href="#" class="btn btn-primary">Buy Now!</a> <a href="#"
									class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<img src="http://placehold.it/800x500" alt="">
						<div class="caption">
							<h3>Feature Label</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
							<p>
								<a href="#" class="btn btn-primary">Buy Now!</a> <a href="#"
									class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<img src="http://placehold.it/800x500" alt="">
						<div class="caption">
							<h3>Feature Label</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
							<p>
								<a href="#" class="btn btn-primary">Buy Now!</a> <a href="#"
									class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>

			</div>
			<!-- /.row -->


		
	</div>
	<!-- /.container -->


	<hr>

	<!-- Footer -->
	<%@include file="includes/footer.jsp"%>


	<%@include file="includes/boostrapjquery.jsp"%>

</f:view>

</body>

</html>
