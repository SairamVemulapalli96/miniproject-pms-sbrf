<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Tenant" %>
<%@include file="dynamicmenu.jsp" %>
		<h1>List of Tenants</h1>
		<%
			List<Tenant> tenantList = new ArrayList<>();
			Object obj = request.getAttribute("tenantList");
			if(null!=obj) {
				tenantList = (List<Tenant>) obj;
			}
		%>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h2>Tenants List </h2>
		Total list of tenants is : <%= tenantList.size() %>
		<pre>
		</pre>
		<%
			if(tenantList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<table border="1">
			<thead>
				<tr>
					<td><b>Tenant Id</b></td>
					<td><b>Name</b></td>
					<td><b>Age</b></td>
					<td><b>Gender</b></td>
					<td><b>Email</b></td>
					
					<td><b>Phone no</b></td>
					<td><b>Address</b></td>
					<td><b>Owner Id</b></td>
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Tenant tenant : tenantList)
			{
				out.println("<tr>");
				out.println("<td class='center'><b>" + tenant.getTId() + " </b>" + 
				"<a href='ViewTenant?tid=" + tenant.getTId() + "'>View " + "</a> | " +
				"<a href='EditTenant?tid=" + tenant.getTId() + "'>Edit</a>  " +
				"</td>");
				out.println("<td>" + tenant.getName() + "</td>");
				out.println("<td class='center'>" + tenant.getAge() + "</td>");
				out.println("<td class='center'>" + tenant.getGender() + "</td>");
				out.println("<td>" + tenant.getEmail() + "</td>");
				out.println("<td>" + tenant.getPhoneno() + "</td>");
				out.println("<td>" + tenant.getAddress() + "</td>");
				out.println("<td>" + tenant.getOId() + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
