<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="includes/boostrapjquery.jsp"%>

<meta charset="US-ASCII">
<title>Product</title>

</head>
<body>
	<div class="container maincontent">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 form-horizontal">
				<h1>${product.name}</h1>
				<hr />
				<h2>Details</h2>
				<div>Code: ${product.code}</div>
				<div>Price: ${product.price}</div>
				<div>Description: ${product.description}</div>

				<a href="<c:url value="/controller/product.list" />">View all
					products</a>
			</div>
		</div>
	</div>

</body>
</html>