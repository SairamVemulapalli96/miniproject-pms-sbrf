
		<h1>Property Management System</h1>
		<%@include file="dynamicmenu.jsp" %>
		<br/><br/>
		<!--  <a href="List">View Owners</a>&nbsp; | &nbsp;
		<a href="createowner.jsp">Add Owner</a>&nbsp; | &nbsp;
		<a href="TenantList">View Tenants</a>&nbsp; | &nbsp;
		<a href="createtenant.jsp">Add Tenant</a>&nbsp; | &nbsp;-->
		<%
			String message = (String) request.getAttribute("message");
			String userInSession = (String) session.getAttribute("user");
			
		
			if(null!=userType) {
		%>			
				<div class="successMsg">Hello <%= userType%></div>
				
		<%		
			}
		%>
		<%@include file="footer.jsp" %>
