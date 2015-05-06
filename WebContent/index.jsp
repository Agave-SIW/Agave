<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">

<head>
<%@include file="includes/head.jsp"%>
<title>Agave Shop</title>
</head>

<body>

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
				<a class="btn btn-primary btn-large"
					href="<c:url value="/shop/product.list" />">View all products</a>
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

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Your Website 2014</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->

	<%@include file="includes/boostrapjquery.jsp"%>

</body>

</html>
