<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="includes/boostrapjquery.jsp"%>

<meta charset="US-ASCII">
<title>Invalid Product</title>

</head>
<body>
	<div class="container maincontent">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 form-horizontal">
				<h1>Invalid Product</h1>
				<hr />
				<div>You are searching a product that does not exist</div>
				<a href="<c:url value="/index.jsp" />">Home</a>
			</div>
		</div>
	</div>
</body>
</html>