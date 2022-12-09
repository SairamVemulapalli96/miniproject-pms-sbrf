<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Property" %>	
<%@include file="dynamicmenu.jsp" %>
		<h1>Edit Property</h1>
		<%
			/* [17Oct2022] - Bug Fix - START */
			/* Issue : 
			   --------
			   Direct hit to Edit does not fetch the right object, instead
			   it reuses the object in session which was stored for the previous
			   operations.
			   Reason being,the id parameter being passed to this page from the
			   list.jsp, has never been considered/used. 
			 */
			Object idParam = request.getParameter("pid");
			int pid = -1;
			if(null!=idParam) {
				pid = Integer.parseInt(idParam.toString());
				out.println("Id parameter passed is : " + pid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Property property = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			property = (Property) request.getAttribute("property");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null==property) {
				property = (Property) session.getAttribute("property");	
			}			
		%>
		<%
			String message = (String) request.getAttribute("message");			
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
			
			if(null==property) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdatePropertyForm" action="UpdateProperty" method="post">
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
								<td>
									${property.getPId()}
									<input type="hidden" name="pid" value="${property.getPId()}"/>
								</td>
							</tr>				
							<tr>
								<td>Property Name</td>
								<td>
									<input type="text" id="propertyname" name="propertyname" size=40 
										placeholder="Your Name" value="${property.getPropertyName()}"
										required />
								</td>
							</tr>
							<tr>
								<td>Door No</td>
								<td>
									<input type="text" id="doorno" name="doorno" size=15
										placeholder="Door No" value="${property.getDoorNo()}"
										required/>
								</td>
							</tr>
							<tr>
								<td>Street Name</td>
								<td>
									<input type="text" id="streetname" name="streetname" size=30 
										placeholder="Street Name" required 
										value="${property.getStreetName()}" required/>
								</td>
							</tr>
							<tr>
								<td>City</td>
								<td>
									<input type="text" id="city" name="city" size=20 
										placeholder="City" 
										value="${property.getCity()}" required/>
								</td>
							</tr>
							<tr>
								<td>Pincode</td>
								<td>
									<input type="text" id="pincode" name="pincode" size=10 
										placeholder="Pincode" 
										value="${property.getPincode()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>State</td>
								<td>
									<input type="text" id="state" name="state" size=20 
										placeholder="state" 
										value="${property.getState()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Country</td>
								<td>
									<input type="text" id="country" name="country" size=30 
										placeholder="Country" 
										value="${property.getCountry()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Area</td>
								<td>
									<input type="text" id="area" name="area" size=30 
										placeholder="Property Area in sqft/sq yards" 
										value="${property.getArea()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Facing</td>
								<td>
									<input type="text" id="facing" name="facing" size=105
										placeholder="Facing" 
										value="${property.getFacing()}" />
								</td>
							</tr>
							
							<tr>
								<td>Purchase Value</td>
								<td>
									<input type="text" id="purchasevalue" name="purchasevalue" size=20 
										placeholder="Purchase Value" 
										value="${property.getPurchaseValue()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Flat No</td>
								<td>
									<input type="text" id="flatno" name="flatno" size=15 
										placeholder="Flat No / Floor No " 
										value="${property.getFlatNo()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Address</td>
								<td>
							<textarea id="address" name="address" rows="2" cols="50" 
								placeholder="Your address" >${property.getAddress()}</textarea>
							</td>
							</tr>
							
							<tr>
								<td>Property Tax</td>
								<td>
									<input type="text" id="propertytax" name="propertytax" size=20 
										placeholder="Property Tax" 
										value="${property.getPropertyTax()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Water Tax</td>
								<td>
									<input type="text" id="watertax" name="watertax" size=20 
										placeholder="Water Tax" 
										value="${property.getWaterTax()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Electricity Charges</td>
								<td>
									<input type="text" id="electrictycharges" name="electricitycharges" size=20 
										placeholder="Electricty Charges" 
										value="${property.getElectricityCharges()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Owner Id</td>
								<td>
									<input type="number" id="oid" name="oid" size=5
										placeholder="Property Owner Id " 
										value="${property.getOId()}" required/>
								</td>
							</tr>	
							
							<tr>
								<td>Owner Name</td>
								<td>
									<input type="text" id="ownername" name="ownername" size=20 
										placeholder="Property Owner Name" 
										value="${property.getOwnerName()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Tenant Id</td>
								<td>
									<input type="number" id="tid" name="tid" size=5
										placeholder="Property Tenant Id " 
										value="${property.getTId()}" />
								</td>
							</tr>	
							
							<tr>
								<td>Tenant Name</td>
								<td>
									<input type="text" id="tenantname" name="tenantname" size=20 
										placeholder="Property Tenant Name" 
										value="${property.getTenantName()}" />
								</td>
							</tr>
							
								
							<tr>
								<td colspan="2">
									<input type="submit" name="Update" Value="Update"/>
								</td>
							</tr>			
						</tbody>
					</table>
				</form>
		<% 		
			}
		%>
		<%@include file="footer.jsp" %>
