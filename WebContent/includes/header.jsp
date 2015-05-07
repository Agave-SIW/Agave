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
			<a class="navbar-brand"
				href="<c:url value="${request.getContextPath()}/" />"> <img
				alt="Agave" src="<%=request.getContextPath()%>/images/logo_small.png" style="width: auto;height: 20px;" />
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="<c:url value="/newProduct.jsp" />">Insert a
						new product</a></li>
			</ul>
			<% if (session.getAttribute("customerLogged")==null || session.getAttribute("customerLogged").equals(false)) { %>
			<form class="navbar-form navbar-right" role="login"
				action="<c:url value="${request.getContextPath()}/shop/customer.login" />"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="email"
						name="email" /> <input type="password" class="form-control"
						placeholder="password" name="password" />
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
			<% } else {  %>
			<ul class="nav navbar-nav navbar-right">
				<li><p class="navbar-text">Benvenuto ${customer.firstName}</p></li>
				<li><a
					href="<c:url value="${request.getContextPath()}/shop/customer.logout" />">Logout</a></li>

			</ul>
			<% } %>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
