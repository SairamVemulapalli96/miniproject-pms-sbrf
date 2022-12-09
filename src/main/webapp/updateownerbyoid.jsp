<%@page import="com.tutorials.jdbc.bo.Owner"%>
<%@include file="dynamicmenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<h1>Edit Owner by OId</h1>
<form  id="updateOwnerForm" name="updateOwnerForm" action="editowner.jsp" method="post">
<table border ="1">
<tr>
<td>
Enter the OId
</td>
<td>
<input type="number" id="oid" name="oid" 
								placeholder="Enter the OId"
								value="" 
								required/>
</td>
</tr>
<tr>
<td  colspan="2">
<input type="submit" name="Edit Owner" Value="Edit Owner"/>
</td>
</tr>
</table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>