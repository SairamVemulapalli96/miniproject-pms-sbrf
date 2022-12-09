<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Agreement" %>	
<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Owner" %>
<%@include file="dynamicmenu.jsp" %>
		<h1>Edit Agreement</h1>
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
			Object idParam = request.getParameter("aid");
			int aid = -1;
			if(null!=idParam) {
				aid = Integer.parseInt(idParam.toString());
				out.println("Id parameter passed is : " + aid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Agreement agreement = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			agreement = (Agreement) request.getAttribute("agreement");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null==agreement) {
				agreement = (Agreement) session.getAttribute("agreement");	
			}			
		%>
		<%
			String message = (String) request.getAttribute("message");			
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Owner owner = (Owner) session.getAttribute("owner");
			
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
			
			if(null==agreement) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateAgreementForm" action="UpdateAgreement" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>Agreement Id</td>
								<td>
									${agreement.getAId()}
									<input type="hidden" name="aid" value="${agreement.getAId()}"/>
								</td>
							</tr>				
							<tr>
							<tr>
								<td>Owner Id</td>
								<td>
									${agreement.getOId()}
									<input type="hidden" name="oid" value="${agreement.getOId()}"/>
								</td>
							</tr>	
							
							<tr>
								<td>Owner Name</td>
								<td>
									<input type="text" id="ownername" name="ownername" size=20 
										placeholder="Property Owner Name" 
										value="${agreement.getOwnerName()}" required/>
								</td>
							</tr>
							
							<tr>
								<td>Property Id</td>
								<td>
									<input type="number" id="pid" name="pid" size=5
										placeholder="Property Id " 
										value="${agreement.getPId()}" required/>
								</td>
							</tr>	
							
								<tr>							
								<td>Property Name</td>
								<td>
									<input type="text" id="propertyname" name="propertyname" size=40 
										placeholder="Your Name" value="${agreement.getPropertyName()}"
										required />
								</td>
							</tr>
							
							<tr>
								<td>Tenant Id</td>
								<td>
									<input type="number" id="tid" name="tid" size=5
										placeholder="Property Tenant Id " 
										value="${agreement.getTId()}" />
								</td>
							</tr>	
							
							<tr>
								<td>Tenant Name</td>
								<td>
									<input type="text" id="tenantname" name="tenantname" size=20 
										placeholder="Tenant Name" 
										value="${agreement.getTenantName()}" />
								</td>
							</tr>
							<tr>
								<td>Tenancy Start Date</td>
								<td>
									<input type="date" id="startdate" name="startdate" size=20 
										placeholder="Tenancy Start Date" required 
										value="${agreement.getTenancyStartDate()}" required/>
								</td>
							</tr>
							<tr>
								<td>Tenancy End Date</td>
								<td>
									<input type="date" id="enddate" name="enddate" size=20 
										placeholder="Tenancy End Date" required 
										value="${agreement.getTenancyEndDate()}" required/>
								</td>
							</tr>
							
	
							<tr>
								<td>Rent</td>
								<td>
									<input type="number" id="rent" name="rent" size=10
										placeholder="Rent " 
										value="${agreement.getRent()}" />
								</td>
							</tr>	
							
							<tr>
								<td>Maintenance</td>
								<td>
									<input type="number" id="maintenance" name="maintenance" size=10
										placeholder="maintenance " 
										value="${agreement.getMaintenance()}" />
								</td>
							</tr>
							
							
							<tr>
								<td>Terms</td>
								<td>
							<textarea id="terms" name="terms" rows="5" cols="40" 
								placeholder="Terms/Conditions" >${agreement.getTerms()}</textarea>
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
