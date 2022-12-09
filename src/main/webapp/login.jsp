<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Property Management System | Login Page</title>
		<link rel="stylesheet" href="style.css"/>
		<script>
		function changeUserType()
	      {
	        var usertype = document.getElementsByName('usertype');
	        var usertypeValue;
	        for(var i = 0; i < usertype.length; i++){
	            if(usertype[i].checked){
	                usertypeValue = usertype[i].value;
	            }
	        }
	        console.log("(generic) selected usertype : " + usertypeValue);
	      }
		
		
		</script>
	</head>
	<body>	
		<%
			if(session.getAttribute("user")!=null) {
				request.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
			}
		%>
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			String message = (String) request.getAttribute("message");
		
			if(null!=errorMessage) {
		%>
				<div class="errorMsg"><%= errorMessage %></div>
		<%
			} else {
				if(null!=message) {
		%>	
				<div class=successMsg><%= message %></div>
		<%	
				}
			}
		%>	
		<h1>Login/SignUp Page</h1>
		<div class="login">
			<form id="loginForm" name="LoginForm" method="post" action="Login">
				<table>
					<thead>
						<tr>
							<td>Field</td>
							<td>Value</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								Email : 
							</td>
							<td>
								<input type="email" id="email" name="email" 
										value="" size="30" required placeholder="Email id"/>
							</td>
						</tr>
						<tr>
							<td>
								Password : 
							</td>
							<td>
								<input type="password" id="password" name="password" 
								value="" size="20" required placeholder="Password"/>
							</td>
						</tr>
						
						<tr>
			
          <td><label for="usertype">User Type : </label></td>
          <td>
            Admin<input type="radio" id="usertype" name="usertype" value="Admin" required onclick="changeUserType();"></input>
            Owner<input type="radio" id="usertype" name="usertype" value="Owner" onclick="changeUserType();"></input>
            Tenant<input type="radio" id="usertype" name="usertype" value="Tenant" onclick="changeUserType();"></input>
           
          </td>
        </tr>
						<tr>
							<td colspan="2">
							  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <input type="submit" name="Login" Value="Login"/>&nbsp;
								<input type="reset" name="Reset" Value="Reset"/>
							</td>
						</tr>
											
					</tbody>
				</table>
			</form>
		</div>
		<div class="register">
		<table>
		<tr>
		<td><b>Not an existing user?</b></td>
		<td><a href="/PropertyManagementSystem/registerowner.jsp"><button>Click here to Register as an Owner</button></a>
		<a href="/PropertyManagementSystem/registertenant.jsp"><button>Click here to Register as a Tenant</button></a> 
		</td>
		</tr>
		</table>
		</div>
		<hr/>
		<%@include file="footer.jsp" %>
	</body>
</html>		