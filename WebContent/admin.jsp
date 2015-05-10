<%@include file="includes/imports.jsp"%><!DOCTYPE html>
<html>

<head>
<%@include file="includes/head.jsp"%>
<title>Admin CP</title>
</head>
<body>
	<f:view>
		<div class="container maincontent">
			<%@include file="includes/header.jsp"%>
			<div class="row">

				<h:panelGroup rendered="#{adminController.notLoggedIn()}">
					<h:form styleClass="form-signin">
						<h2 class="form-signin-heading">Please sign in</h2>


						<label for="email" class="sr-only">Email address</label>
						<h:inputText value="#{adminController.email}" required="true"
							requiredMessage="Email is mandatory" id="email"
							styleClass="form-control needPlaceholder needRequired needType" />
						<!--  placeholder="Email" -->
						<h:message for="email" styleClass="label label-warning" />

						<label for="password" class="sr-only">Password</label>
						<h:inputSecret value="#{adminController.password}" required="true"
							requiredMessage="Password is mandatory" id="password"
							styleClass="form-control needPlaceholder needRequired" />
						<!--  placeholder="Email" -->
						<h:message for="password" styleClass="label label-warning" />


						<div class="form-group">
							<h:commandButton value="Login"
								action="#{adminController.loginAdmin}"
								styleClass="btn btn-primary btn-block" />
						</div>

					</h:form>
				</h:panelGroup>
				<h:panelGroup rendered="#{adminController.loggedIn()}">
					<h:form styleClass="form-signin">


						<p>
							Benvenuto
							<h:outputText
								value="#{adminController.getCurrentAdmin().getFirstName()}" />
							-
							<h:commandLink value="Logout"
								action="#{adminController.logoutAdmin}" />
						</p>


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