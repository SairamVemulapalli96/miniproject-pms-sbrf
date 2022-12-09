<%@page import="com.tutorials.jdbc.bo.Agreement"%>
<%@page import="com.tutorials.jdbc.bo.Owner"%>
<%@include file="dynamicmenu.jsp" %>

		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Agreement agreement = (Agreement) request.getAttribute("agreementForm");
			Owner owner = (Owner) session.getAttribute("owner");
			
			boolean isError = (null!=agreement);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Add Property</h1>
		<form id="createAgreementForm" name="createAgreementForm" action="CreateAgreement" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>Field</th>
						<th>Value</th>
					</tr>				
				</thead>
				<tbody>	
					<tr>
						<td>Owner Id</td>
						<td>${owner.getOId()}
							<input type="hidden" name="oid" value="${owner.getOId()}"/></td>
						
					</tr>
					
					<tr>
						<td>Owner Name</td>
						<td>
							${owner.getName()}
							<input type="hidden" name="ownername" value="${owner.getName()}"/>
						</td>
					</tr>
					<tr>
						<td>Property Id</td>
						<td>
							<%						
								int pid = isError ? agreement.getPId() : 0;
							%>
							<input type="number" id="pid" name="pid" 
								step="1" size="5"
								placeholder="Property Id"
								value="<%=pid%>" 
								required/>
						</td>
					</tr>
					<tr>
						<td>Property Name</td>
						<td>
							<input type="text" id="propertyname" name="propertyname" size=40 
								placeholder="Your Property Name"
								value="<% if(isError) { 
									out.println(agreement.getPropertyName());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					
					<tr>
						<td>Tenant Id</td>
						<td>
							<%						
								int tid = isError ? agreement.getTId() : 0;
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
									out.println(agreement.getTenantName());
								} else {
									out.println("");
								}%>" />
						</td>
					</tr>
					
					<tr>
						<td>Tenancy Start Date</td>
						<td>
							<input type="date" id="startdate" name="startdate" 
								placeholder="Tenancy Start Date" required 
								value="<% if(isError) { 
									out.println(agreement.getTenancyStartDate());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					<tr>
						<td>Tenancy End Date</td>
						<td>
							<input type="date" id="enddate" name="enddate" 
								placeholder="Tenancy End Date" required 
								value="<% if(isError) { 
									out.println(agreement.getTenancyEndDate());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Rent</td>
						<td>
							<input type="number" id="rent" name="rent" size=10 
								placeholder="Rent" 
								value="<% if(isError) { 
									out.println(agreement.getRent());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Maintenance</td>
						<td>
							<input type="number" id="maintenance" name="maintenance" size=10 
								placeholder="Maintenance" 
								value="<% if(isError) { 
									out.println(agreement.getMaintenance());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					
					<tr>
						<td>Terms</td>
						<td>
							<textarea id="terms" name="terms" rows="5" cols="40" 
								placeholder="Your Terms/Conditions" 
								required>
								<% if(isError) { 
									out.println(agreement.getTerms());
								} else {
									out.println("");
								}%>
								</textarea>
						
						</td>
					</tr>
					
					
					
					<tr>
						<td colspan="2">
							<input type="submit" name="Add Agreement" Value="Add Agreement"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		<%@include file="footer.jsp" %>
