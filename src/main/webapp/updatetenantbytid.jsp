<%@page import="com.tutorials.jdbc.bo.Tenant"%>
<%@include file="dynamicmenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<h1>Edit Tenant by TId</h1>
<form  id="updateTenantForm" name="updateTenantForm" action="edittenant.jsp" method="post">
<table border ="1">
<tr>
<td>
Enter the TId
</td>
<td>
<input type="number" id="tid" name="tid" 
								placeholder="Enter the TId"
								value="" 
								required/>
</td>
</tr>
<tr>
<td  colspan="2">
<input type="submit" name="Edit Tenant" Value="Edit Tenant"/>
</td>
</tr>
</table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>