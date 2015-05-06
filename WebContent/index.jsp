<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="includes/boostrapjquery.jsp"%>

<meta charset="US-ASCII">
<title>siw-jee-es1</title>

</head>
<body>
	<div class="container maincontent">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<h1>siw-jee-es1</h1>
				<hr />
				<a href="<c:url value="/newProduct.jsp" />">Insert a new product</a>
				<br />
				<a href="<c:url value="/controller/product.list" />">View all products</a>
			</div>
		</div>
	</div>
</body>
</html>