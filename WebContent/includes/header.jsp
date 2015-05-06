<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<%=request.getContextPath()%>">Agave
				Shop</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="<c:url value="/newProduct.jsp" />">Insert a
						new product</a></li>
			</ul>
			<form class="navbar-form navbar-right" role="login"
				action="<c:url value="/shop/customer.login" />" method="get">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="email"
						name="email" /> <input type="password" class="form-control"
						placeholder="password" name="password" />
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
