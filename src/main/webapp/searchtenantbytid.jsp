<%@page import="com.tutorials.jdbc.bo.Tenant"%>
<%@include file="dynamicmenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<h1>View Tenant by Tenant Id</h1>
<form  id="searchTenantForm" name="searchTenantForm" action="ViewTenant" method="get">
<table border ="1">
<tr>
<td>
Enter the Tenant Id
</td>
<td>
<input type="number" id="tid" name="tid" 
								placeholder="Tenant Id"
								value="" 
								required/>
</td>
</tr>
<tr>
<td  colspan="2">
<input type="submit" name="View Tenant" Value="View Tenant"/>
</td>
</tr>
</table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>