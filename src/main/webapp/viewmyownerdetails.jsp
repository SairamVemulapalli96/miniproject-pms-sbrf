<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Owner" %>
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
		<h1>View Owner</h1>
		<%
			Owner owner = (Owner) request.getAttribute("myowner");
		
			if(null!=owner) {
				session.setAttribute("owner", owner);
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
					<td>Name</td>
					<td>${owner.name}</td>
				</tr>
				<tr>
					<td>Age</td>
					<td>${owner.age}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${owner.gender}</td>
				</tr>
				<tr>
					<td>Phone no</td>
					<td>${owner.phoneno}</td>
				</tr>
				<tr>
					<td>Address</td>
					<td>${owner.address}</td>
				</tr>				
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.oid}.</div>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
