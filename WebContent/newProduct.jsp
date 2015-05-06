<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<%@include file="includes/head.jsp"%>
	<title>New Product</title>
</head>
<body>
	<div class="container maincontent">
		<%@include file="includes/header.jsp"%>
		<div class="row">
			<form action="<c:url value="/shop/product.create" />" method="get"
				class="col-md-6 col-md-offset-3 form-horizontal">
				<div class="form-group">
					<h1>New Product</h1>
					<hr />
				</div>
				<div class="form-group">
					<label for="name">Name: </label> <input type="text" id="name"
						name="name" value="${param.name}" class="form-control" required /> <span
						style="color: red">${nameErr}</span>
				</div>
				<div class="form-group">
					<label for="code">Code: </label> <input type="text" id="code"
						name="code" value="${param.code}" class="form-control" required /> <span
						style="color: red">${codeErr}</span>
				</div>
				<div class="form-group">
					<label for="price">Price: </label> <input type="text" id="price"
						name="price" value="${param.price}" class="form-control" required /> <span
						style="color: red">${priceErr}</span>
				</div>
				<div class="form-group">
					<label for="description">Description: </label>
					<textarea rows="4" cols="50" id="description" name="description"
						rows="3" class="form-control">${param.description}</textarea>
				</div>
				<div class="form-group" style="padding-top: 20px">
					<input type="submit" name="sumbit" value="Submit"
						class="btn btn-primary" />
					<button type="reset" class="btn btn-default">Reset</button>
				</div>
			</form>
		</div>
	</div>

	<%@include file="includes/boostrapjquery.jsp"%>
</body>
</html>
