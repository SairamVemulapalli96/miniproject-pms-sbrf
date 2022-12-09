<%@page import="com.tutorials.jdbc.bo.Property"%>
<%@include file="dynamicmenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<h1>View Property by Property Id</h1>
<form  id="searchPropertyForm" name="searchPropertyForm" action="ViewProperty" method="get">
<table border ="1">
<tr>
<td>
Enter the Property Id(PId)
</td>
<td>
<input type="number" id="pid" name="pid" 
								placeholder="Property Id"
								value="" 
								required/>
</td>
</tr>
<tr>
<td  colspan="2">
<input type="submit" name="View Property" Value="View Property"/>
</td>
</tr>
</table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>