<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="includes/boostrapjquery.jsp"%>	
	
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Product</title>

</head>
<body>
	<div class="container maincontent">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 form-horizontal">
				<h1>Products</h1>
				<hr />
				<table>
					<tr>
						<th>Name</th>
						<th>Price</th>
					</tr>
					<c:forEach var="product" items="${products}">
						<tr>
							<td><a
								href="<c:url value="/controller/product.get?id=${product.id}" />">${product.name}</a></td>
							<td>${product.price}</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</body>

</html>