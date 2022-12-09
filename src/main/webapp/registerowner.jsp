<%@page import="com.tutorials.jdbc.bo.Owner"%>
<link rel="stylesheet" href="style.css"/>

		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Owner owner = (Owner) request.getAttribute("ownerForm");
			
			boolean isError = (null!=owner);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Please fill the details below to register as an Owner</h1>
		<form id="registerOwnerForm" name="registerOwnerForm" action="RegisterOwner" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>Field</th>
						<th>Value</th>
					</tr>				
				</thead>
				<tbody>	
					<tr>
						<td>Name</td>
						<td>
							<input type="text" id="name" name="name" size=20 
								placeholder="Your Name"
								value="<% if(isError) { 
									out.println(owner.getName());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Age</td>
						<td>
							<%						
								int age = isError ? owner.getAge() : 18;
							%>
							<input type="number" id="age" name="age" 
								min="1" max="100" step="1" size="5"
								placeholder="Your Age"
								value="<%=age%>" 
								required/>
						</td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>
							<input type="radio" name="gender" id="genderM" value="M" 
							<%
								if(isError && owner.getGender()=='M') {
									out.println(" checked");
								}
							
								if(!isError) {
									
								}
							%>>M
							<input type="radio" name="gender" id="genderF" value="F"
							<%
								if(isError && owner.getGender()=='F') {
									out.println(" checked");
								}
							%>>F
						</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>
							<input type="email" id="email" name="email" size=20 
								placeholder="Your Email" required 
								value="<% if(isError) { 
									out.println(owner.getEmail());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<input type="password" id="password" name="password" size=20 
								placeholder="Enter your Password" 
								value="<% if(isError) { 
									out.println(owner.getPassword());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					<tr>
						<td>Phoneno</td>
						<td>
							<input type="text" id="phoneno" name="phoneno" size=15 
								placeholder="Your Phone no" 
								value="<% if(isError) { 
									out.println(owner.getPhoneno());
								} else {
									out.println("");
								}%>" required/>
						</td>
					</tr>
					
					<tr>
						<td>Address</td>
						<td>
							<textarea id="address" name="address" rows="2" cols="15" maxlength="30"
								placeholder="Your address" 
								 required><% if(isError) { 
									out.println(owner.getAddress());
								} else {
									out.println("");
								}%></textarea>
						</td>
						
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" name="Register" Value="Register"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		<%@include file="footer.jsp" %>
