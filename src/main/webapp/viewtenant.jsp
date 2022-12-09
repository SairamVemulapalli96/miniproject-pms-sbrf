<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Tenant" %>
<%@include file="dynamicmenu.jsp" %>

		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>View Tenant</h1>
		<%
			Tenant tenant = (Tenant) request.getAttribute("tenant");
		
			if(null!=tenant) {
				session.setAttribute("tenant", tenant);
		%>
		<table border="1">
			<thead>
				<tr>
					<td>Field</td>
					<td>Value</td>
				</tr>				
			</thead>
			<tbody>	
				<tr>
					<td>Tenant Id</td>
					<td><%= tenant.getTId() %></td>
				</tr>
				<tr>
					<td>Name</td>
					<td>${tenant.name}</td>
				</tr>
				<tr>
					<td>Age</td>
					<td>${tenant.age}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${tenant.gender}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${tenant.email}</td>
				</tr>
				<tr>
					<td>Phone no</td>
					<td>${tenant.phoneno}</td>
				</tr>
				<tr>
					<td>Address</td>
					<td>${tenant.address}</td>
				</tr>	
				<tr>
					<td>Owner Id</td>
					<td><%= tenant.getOId() %></td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="edittenant.jsp">Edit</a> &nbsp;&nbsp;
						<a href="DeleteTenant?tid=${tenant.getTId()}">Delete</a>&nbsp;&nbsp;
						
					</td>
				</tr>			
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.tid}.</div>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
