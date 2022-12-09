<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Owner" %>
<%@include file="dynamicmenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete My Profile</title>
<script>
function changeDecision()
{
  var decision = document.getElementsByName('decision');
  var decisionValue;
  for(var i = 0; i < decision.length; i++){
      if(decision[i].checked){
    	  decisionValue = decision[i].value;
      }
  }
  console.log("(generic) selected decision : " + decisionValue);
}

</script>
</head>
<body>
<h1></h1>
		<%
			//String message = (String) request.getAttribute("message");
			String userInSession = (String) session.getAttribute("user");
		
			if(null!=userInSession) {
		%>			
				
		<%		
			}
		%> 
		<p><b>Oh No <%= userInSession%>! We would not like to lose you. Please tell us if you have any suggestions/concerns in the text box below</b></p>
		<form id="deleteForm" name="DeleteMyOwnerProfileForm" action="DeletMyOwnerProfile" method="post">
		<table  border="1">
		<thead>
					<tr>
						<td>Please write your suggestions/concerns.</td>
						<td><textarea id="suggestions" name="suggestions" rows="5" cols="50" placeholder="Your suggestions/concerns" required></textarea></td>
					</tr>	
					<tr>
					<td>Select <b>"Yes"</b> if you still want to delete your profile, or <b>"No"</b> to submit your suggestions</td>
					<td>
					Yes<input type="radio" id="decision" name="decision" value="Yes" required onclick="changeDecision();"></input>
            		No<input type="radio" id="decision" name="decision" value="No" onclick="changeDecision();"></input>
					</td>
					</tr>	
					<tr>
							<td colspan="2">
							  &nbsp; <input type="submit" name="Submit" Value="Submit"/>&nbsp;
							</td>
						</tr>	
				</thead>
				</table>
		</form>
		<%@include file="footer.jsp" %>
	
</body>
</html>