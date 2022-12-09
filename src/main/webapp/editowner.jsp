<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Owner" %>	
<%@include file="dynamicmenu.jsp" %>
		<h1>Edit Owner</h1>
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
			Object idParam = request.getParameter("oid");
			int oid = -1;
			if(null!=idParam) {
				oid = Integer.parseInt(idParam.toString());
				out.println("Id parameter passed is : " + oid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Owner owner = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			owner = (Owner) request.getAttribute("owner");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null==owner) {
				owner = (Owner) session.getAttribute("owner");	
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
			
			if(null==owner) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateOwnerForm" action="UpdateOwner" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>Owner Id</td>
								<td>
									${owner.getOId()}
									<input type="hidden" name="oid" value="${owner.getOId()}"/>
								</td>
							</tr>				
							<tr>
								<td>Name</td>
								<td>
									<input type="text" id="name" name="name" size=10 
										placeholder="Your Name" value="${owner.name}"
										required />
								</td>
							</tr>
							<tr>
								<td>Age</td>
								<td>
									<input type="number" id="age" name="age" 
										min="1" max="100" step="1" size="5"
										placeholder="Your Age" value="${owner.age}"
										required/>
								</td>
							</tr>
							<tr>
								<td>Gender</td>
								<td>
									<input type="radio" name="gender" id="genderM" value="M" 
									<%
										if(owner.getGender()=='M') {
											out.println(" checked");
										}
									%>>M
									<input type="radio" name="gender" id="genderF" value="F"
									<%
										if(owner.getGender()=='F') {
											out.println(" checked");
										}
									%>>F
								</td>
							</tr>
							<tr>
								<td>Email</td>
								<td>
									<input type="text" id="email" name="email" size=20 
										placeholder="Your Email" required 
										value="${owner.email}"/>
								</td>
							</tr>
							<tr>
							<!--	<td>Password</td>
								<td>
									<input type="password" id="password" name="password" size=20 
										placeholder="" 
										value="${owner.password}"/>
								</td>-->
							</tr>
							<tr>
								<td>Phoneno</td>
								<td>
									<input type="text" id="phoneno" name="phoneno" size=10 
										placeholder="Your Phone Number" 
										value="${owner.phoneno}"/>
								</td>
							</tr>
							<tr>
								<td>Address</td>
								<td>
							<textarea id="address" name="address" rows="5" cols="50" 
								placeholder="Your address">${owner.address}</textarea>
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
