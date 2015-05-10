<%@include file="imports.jsp"%>
<f:subview id="adminController">
<h:panelGroup rendered="#{adminController.loggedIn()}">
<nav class="navbar navbar-default navbar-lower" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-2">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="<c:url value="${request.getContextPath()}/shop/admin.jsp" />"> Admin CP
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-2">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/shop/newProduct.jsp" />">Insert
						a new product</a></li>
			</ul>
					<h:form styleClass="navbar-form navbar-right" style="margin: 0;">

						<div class="form-group">
							<ul class="nav navbar-nav navbar-right">
								<li><p class="navbar-text">
										Welcome
										<h:outputText
											value="#{adminController.getCurrentAdmin().getFirstName()}" />
									</p></li>
								<li><h:commandLink value="Logout"
										action="#{adminController.logoutAdmin}" /></li>

							</ul>
						</div>
					</h:form>
				

			


		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
</h:panelGroup>
</f:subview>