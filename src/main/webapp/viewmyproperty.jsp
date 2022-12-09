<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Property" %>
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
		<h1>View Property</h1>
		<%
			Property property = (Property) request.getAttribute("property");
		
			if(null!=property) {
				session.setAttribute("property", property);
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
					<td>Property Id</td>
					<td><%= property.getPId() %></td>
				</tr>
				<tr>
					<td>Property Name</td>
					<td><%= property.getPropertyName() %></td>
				</tr>
				<tr>
					<td>Door No</td>
					<td><%= property.getDoorNo() %></td>
				</tr>
				<tr>
					<td>Street Name</td>
					<td><%= property.getStreetName() %></td>
				</tr>
				<tr>
					<td>City</td>
					<td><%= property.getCity() %></td>
				</tr>
				<tr>
					<td>Pincode</td>
					<td><%= property.getPincode() %></td>
				</tr>
				<tr>
					<td>State</td>
					<td><%= property.getState() %></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><%= property.getCountry() %></td>
				</tr>
				
				<tr>
					<td>Area</td>
					<td><%= property.getArea() %></td>
				</tr>
				
				<tr>
					<td>Facing</td>
					<td><%= property.getFacing() %></td>
				</tr>
				
				<tr>
					<td>Purchase Value</td>
					<td><%= property.getPurchaseValue() %></td>
				</tr>	
				
				<tr>
					<td>Flat No</td>
					<td><%= property.getFlatNo() %></td>
				</tr>	
				
				<tr>
					<td>Address</td>
					<td><%= property.getAddress() %></td>
				</tr>	
				
				<tr>
					<td>Property Tax</td>
					<td><%= property.getPropertyTax() %></td>
				</tr>	
				
				<tr>
					<td>Water Tax</td>
					<td><%= property.getWaterTax() %></td>
				</tr>
				
				<tr>
					<td>Electricity Charges</td>
					<td><%= property.getElectricityCharges() %></td>
				</tr>
				
				<tr>
					<td>Owner Id</td>
					<td><%= property.getOId() %></td>
				</tr>
				
				<tr>
					<td>Owner Name</td>
					<td><%= property.getOwnerName() %></td>
				</tr>
				
				<tr>
					<td>Tenant Id</td>
					<td><%= property.getTId() %></td>
				</tr>
				
				<tr>
					<td>Tenant Name</td>
					<td><%= property.getTenantName() %></td>
				</tr>	
				
				
				
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="editproperty.jsp">Edit</a> &nbsp;&nbsp;
						<a href="DeleteMyProperty?pid=${property.getPId()}">Delete</a>&nbsp;&nbsp;
						
					</td>
				</tr>			
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.pid}.</div>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
