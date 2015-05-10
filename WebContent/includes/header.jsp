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
				alt="Agave"
				src="<%=request.getContextPath()%>/images/logo_small.png"
				style="width: auto; height: 20px;" />
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="<c:url value="/shop/admin.jsp" />">Admin CP</a></li>
			</ul>
			<%@include file="imports.jsp"%>
			<f:subview id="customerController">
				<h:panelGroup rendered="#{customerController.notLoggedIn()}">
					<h:form styleClass="navbar-form navbar-right">

						<div class="form-group">

							<h:inputText value="#{customerController.email}" required="true"
								requiredMessage="Email is mandatory" id="email"
								styleClass="form-control needPlaceholder needRequired" />
							<h:message for="email" styleClass="label label-warning" />
						</div>
						<div class="form-group">
							<h:inputSecret value="#{customerController.password}"
								required="true" requiredMessage="Password is mandatory"
								id="password" styleClass="form-control needPlaceholder needRequired" />
							<h:message for="password" styleClass="label label-warning" />

						</div>
						<div class="form-group">
							<h:commandButton value="Login"
								action="#{customerController.loginCustomer}"
								styleClass="btn btn-primary" />
						</div>

					</h:form>
				</h:panelGroup>
				<h:panelGroup rendered="#{customerController.loggedIn()}">
					<h:form styleClass="navbar-form navbar-right" style="margin: 0;">

						<div class="form-group">
							<ul class="nav navbar-nav navbar-right">
								<li><p class="navbar-text">
										Welcome
										<h:outputText
											value="#{customerController.getCurrentCustomer().getFirstName()}" />
									</p></li>
								<li><h:commandLink value="Logout"
										action="#{customerController.logoutCustomer}" /></li>

							</ul>
						</div>
					</h:form>
				</h:panelGroup>

			</f:subview>


		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
<%@include file="adminMenu.jsp"%>