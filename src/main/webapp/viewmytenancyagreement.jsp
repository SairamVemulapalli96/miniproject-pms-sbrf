<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Agreement" %>
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
		<h1>View Agreement Details</h1>
		<%
			Agreement agreement = (Agreement) request.getAttribute("agreement");
		
			if(null!=agreement) {
				session.setAttribute("agreement", agreement);
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
					<td>Owner Name</td>
					<td><%= agreement.getOwnerName() %></td>
				</tr>
				<tr>
					<td>Property Name</td>
					<td><%= agreement.getPropertyName() %></td>
				</tr>
				<tr>
					<td>Tenant Name</td>
					<td><%= agreement.getTenantName() %></td>
				</tr>	
				<tr>
					<td>Tenancy Start Date</td>
					<td><%= agreement.getTenancyStartDate() %></td>
				</tr>
				<tr>
					<td>Tenancy End Date</td>
					<td><%= agreement.getTenancyEndDate() %></td>
				</tr>
				<tr>
					<td>Rent</td>
					<td><%= agreement.getRent() %></td>
				</tr>
				<tr>
					<td>Maintenance</td>
					<td><%= agreement.getMaintenance() %></td>
				</tr>
				<tr>
					<td>Terms</td>
					<td><%= agreement.getTerms() %></td>
				</tr>
				
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No Agreement Details available</div>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
