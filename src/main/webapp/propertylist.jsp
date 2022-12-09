<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Property" %>
<%@include file="dynamicmenu.jsp" %>
		<h1>List of Properties</h1>
		<%
			List<Property> propertyList = new ArrayList<>();
			Object obj = request.getAttribute("propertyList");
			if(null!=obj) {
				propertyList = (List<Property>) obj;
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
		<h2>Properties List </h2>
		Total list of properties is : <%= propertyList.size() %>
		<pre>
		</pre>
		<%
			if(propertyList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<table border="1">
			<thead>
				<tr>
					<td><b>Property Id</b></td>
					<td><b>Property Name</b></td>
					<td><b>Flat No</b></td>
					<td><b>Street Name</b></td>
					<td><b>City</b></td>
					<td><b>Address</b></td>
					<td><b>Owner Id</b></td>
					<td><b>Owner Name</b></td>
					<td><b>Tenant Id</b></td>
					<td><b>Tenant Name</b></td>
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Property property : propertyList)
			{
				out.println("<tr>");
				out.println("<td class='center'><b>" + property.getPId() + "</b> " + 
				"<a href='ViewProperty?pid=" + property.getPId() + "'> View " + "</a> | " +
				"<a href='EditProperty?pid=" + property.getPId() + "'>Edit</a>  " +
				"</td>");
				out.println("<td>" + property.getPropertyName() + "</td>");
				out.println("<td>" + property.getFlatNo() + "</td>");
				out.println("<td>" + property.getStreetName() + "</td>");
				out.println("<td>" + property.getCity() + "</td>");
				out.println("<td>" + property.getAddress() + "</td>");
				out.println("<td>" + property.getOId() + "</td>");
				out.println("<td>" + property.getOwnerName() + "</td>");
				out.println("<td>" + property.getTId() + "</td>");
				out.println("<td>" + property.getTenantName() + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
