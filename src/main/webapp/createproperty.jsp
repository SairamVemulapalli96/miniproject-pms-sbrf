<%@page import="com.tutorials.jdbc.bo.Property"%>
<%@include file="dynamicmenu.jsp" %>

		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Property property = (Property) request.getAttribute("propertyForm");
			
			boolean isError = (null!=property);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Add Property</h1>
		<form id="createPropertyForm" name="createPropertyForm" action="CreateProperty" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>Field</th>
						<th>Value</th>
					</tr>				
				</thead>
				<tbody>	
					<tr>
						<td>Property Name</td>
						<td>
							<input type="text" id="propertyname" name="propertyname" size=40 
								placeholder="Your Property Name"
								value="<% if(isError) { 
									out.println(property.getPropertyName());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					
					<tr>
						<td>Door No</td>
						<td>
							<input type="text" id="doorno" name="doorno" size=15 
								placeholder="Property door No" required 
								value="<% if(isError) { 
									out.println(property.getDoorNo());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					<tr>
						<td>Street Name</td>
						<td>
							<input type="text" id="streetname" name="streetname" size=30 
								placeholder="Street Name" 
								value="<% if(isError) { 
									out.println(property.getStreetName());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					<tr>
						<td>City</td>
						<td>
							<input type="text" id="city" name="city" size=20 
								placeholder="Enter City Name" 
								value="<% if(isError) { 
									out.println(property.getCity());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Pincode</td>
						<td>
							<input type="text" id="pincode" name="pincode" size=10 
								placeholder="Enter Pincode" 
								value="<% if(isError) { 
									out.println(property.getPincode());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>State</td>
						<td>
							<input type="text" id="state" name="state" size=20 
								placeholder="Enter State Name" 
								value="<% if(isError) { 
									out.println(property.getState());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Country</td>
						<td>
							<input type="text" id="country" name="country" size=30 
								placeholder="Enter Country Name" 
								value="<% if(isError) { 
									out.println(property.getCountry());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Area</td>
						<td>
							<input type="text" id="area" name="area" size=30 
								placeholder="Enter Property Area(sqft/sq yards)" 
								value="<% if(isError) { 
									out.println(property.getArea());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Facing</td>
						<td>
							<input type="text" id="facing" name="facing" size=15 
								placeholder="Facing" 
								value="<% if(isError) { 
									out.println(property.getFacing());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Purchase Value</td>
						<td>
							<input type="text" id="purchasevalue" name="purchasevalue" size=20 
								placeholder="Enter Purchase Value" 
								value="<% if(isError) { 
									out.println(property.getPurchaseValue());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Flat No</td>
						<td>
							<input type="text" id="flatno" name="flatno" size=20 
								placeholder="Enter Flat no/Floor" 
								value="<% if(isError) { 
									out.println(property.getFlatNo());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Address</td>
						<td>
							<textarea id="address" name="address" rows="2" cols="50" 
								placeholder="Your address" 
								required>
								<% if(isError) { 
									out.println(property.getAddress());
								} else {
									out.println("");
								}%>
								</textarea>
						
						</td>
					</tr>
					
					<tr>
						<td>Property Tax</td>
						<td>
							<input type="text" id="propertytax" name="propertytax" size=20 
								placeholder="Property Tax" 
								value="<% if(isError) { 
									out.println(property.getPropertyTax());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Water Tax</td>
						<td>
							<input type="text" id="watertax" name="watertax" size=20 
								placeholder="Water Tax" 
								value="<% if(isError) { 
									out.println(property.getWaterTax());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Electricity Charges </td>
						<td>
							<input type="text" id="electricitycharges" name="electricitycharges" size=20 
								placeholder="Electricity Charges" 
								value="<% if(isError) { 
									out.println(property.getElectricityCharges());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Owner Id</td>
						<td>
							<%						
								int oid = isError ? property.getOId() : 0;
							%>
							<input type="number" id="oid" name="oid" 
								step="1" size="5"
								placeholder="Property Owner Id"
								value="<%=oid%>" 
								required/>
						</td>
					</tr>
					
					<tr>
						<td>Owner Name</td>
						<td>
							<input type="text" id="ownername" name="ownername" size=20 
								placeholder="Owner Name" 
								value="<% if(isError) { 
									out.println(property.getOwnerName());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Tenant Id</td>
						<td>
							<%						
								int tid = isError ? property.getTId() : 0;
							%>
							<input type="number" id="tid" name="tid" 
								step="1" size="5"
								placeholder="Property Tenant Id"
								value="<%=tid%>" 
								/>
						</td>
					</tr>
					
					<tr>
						<td>Tenant Name</td>
						<td>
							<input type="text" id="tenantname" name="tenantname" size=20 
								placeholder="Tenant Name" 
								value="<% if(isError) { 
									out.println(property.getTenantName());
								} else {
									out.println("");
								}%>" />
						</td>
					</tr>
					
					
					<tr>
						<td colspan="2">
							<input type="submit" name="Add Property" Value="Add Property"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		<%@include file="footer.jsp" %>
