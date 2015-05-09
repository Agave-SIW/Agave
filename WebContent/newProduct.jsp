<%@include file="includes/imports.jsp"%><!DOCTYPE html>
<html>

<head>
<%@include file="includes/head.jsp"%>
<title>New Product</title>
</head>
<body>
	<f:view>
		<div class="container maincontent">
			<%@include file="includes/header.jsp"%>
			<div class="row">

				<h:form styleClass="col-md-6 col-md-offset-3 form-horizontal">
					<div class="form-group">
						<h1>New Product</h1>
						<span style="color: red">TODO: questa pagina va spostata
							nel pannello admin</span>
						<hr />
					</div>
					<div class="form-group">
						<label for="name">Name: </label>

						<h:inputText value="#{productController.name}" required="true"
							requiredMessage="Il nome e' obbligatorio" id="name"
							styleClass="form-control" />
						<h:message for="name" style="color: red" />

					</div>
					<div class="form-group">

						<label for="code">Code: </label>

						<h:inputText value="#{productController.code}" required="true"
							requiredMessage="Il codice prodotto e' obbligatorio" id="code"
							styleClass="form-control" />
						<h:message for="code" style="color: red" />

					</div>
					<div class="form-group">
						<label for="price">Price: </label>

						<h:inputText value="#{productController.price}" required="true"
							requiredMessage="Il prezzo e' obbligatorio" id="price"
							styleClass="form-control" />
						<h:message for="price" style="color: red" />

					</div>
					<div class="form-group">
						<label for="description">Description: </label>

						<h:inputTextarea value="#{productController.description}"
							required="false" cols="20" rows="5" id="description"
							styleClass="form-control" />

					</div>
					<div class="form-group" style="padding-top: 20px">

						<h:commandButton value="Submit"
							action="#{productController.createProduct}"
							styleClass="btn btn-primary" />
						<button type="reset" class="btn btn-default">Reset</button>
					</div>
				</h:form>




				<span style="color: red"
					class="col-md-6 col-md-offset-3 form-horizontal">TODO:
					questo &egrave; un test per il login admin</span>


				<h:panelGroup rendered="#{adminController.notLoggedIn()}">

					<h:form styleClass="col-md-6 col-md-offset-3 form-horizontal">

						<div class="form-group">

							<h:inputText value="#{adminController.email}" required="true"
								requiredMessage="la mail e' obbligatoria" id="email"
								styleClass="form-control" />
							<!--  placeholder="Email" -->
							<h:message for="email" style="color: red" />

							<h:inputSecret value="#{adminController.password}"
								required="true" requiredMessage="La password e' obbligatoria"
								id="password" styleClass="form-control" />
							<!--  placeholder="Email" -->
							<h:message for="password" style="color: red" />

						</div>
						<h:commandButton value="Login"
							action="#{adminController.loginAdmin}"
							styleClass="btn btn-primary" />

					</h:form>
				</h:panelGroup>
				<h:panelGroup rendered="#{adminController.loggedIn()}">
					<h:form styleClass="navbar-form navbar-right" style="margin: 0;">

						<div class="form-group">
							<p>
								Benvenuto
								<h:outputText
									value="#{adminController.getCurrentAdmin().getFirstName()}" />
								-
								<h:commandLink value="Logout"
									action="#{adminController.logoutAdmin}" />
							</p>

						</div>
					</h:form>
				</h:panelGroup>

			</div>

		</div>

		<!-- /.container -->

		<!-- Footer -->
		<%@include file="includes/footer.jsp"%>


		<%@include file="includes/boostrapjquery.jsp"%>

		<%@include file="includes/boostrapjquery.jsp"%>
	</f:view>
</body>
</html>
