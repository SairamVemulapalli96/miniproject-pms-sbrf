<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Property" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>

		<%
			//String message = (String) request.getAttribute("message");
			String userType = (String) session.getAttribute("usertype");
			String user = (String) session.getAttribute("user");
			
		
			if(userType.equals("Admin")) {
		%>			
				<div class="successMsg"><b><%= userType%></b></div><br/>
				<div class="ownermenu">
	<a href="/PropertyManagementSystem"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="createowner.jsp"><button><b>Add Owner</b></button></a>&nbsp; | &nbsp;
	<a href="List"><button><b>View All Owners</b></button></a>&nbsp; | &nbsp;
	<a href="searchownerbyoid.jsp"><button><b>View Owner</b></button></a><br/><br/>
	
	<a href="createtenant.jsp"><button><b>Add Tenant</b></button></a>&nbsp; | &nbsp;
	<a href="TenantList"><button><b>View All Tenants</b></button></a>&nbsp; | &nbsp;
	<a href="searchtenantbyoid.jsp"><button><b>View Tenants of an Owner</b></button></a>&nbsp; | &nbsp;
	<a href="searchtenantbytid.jsp"><button><b>View Tenant</b></button></a><br/><br/>
	
	<a href="PropertyList"><button><b>View All Properties</b></button></a>&nbsp; | &nbsp;
	<a href="createproperty.jsp"><button><b>Add Property</b></button></a>&nbsp; | &nbsp;
	<a href="searchpropertybypid.jsp"><button><b>View Property</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; &nbsp;
	
</div>
				
		<%		
			}
		%>
		
		<%
		if(userType.equals("Owner")) {
		%>
		<div class="successMsg"><b><%= user%></b></div><br/>
		<div class="ownermenu">
	<a href="ownerindex.jsp"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="ViewMyDetails"><button><b>View/Edit My Details</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyProperties"><button><b>View My Properties</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyAgreements"><button><b>View My Tenancy Agreements</b></button></a>&nbsp; | &nbsp;
	<a href="createagreement.jsp"><button><b>Add Tenancy Agreement</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyTenants"><button><b>View My Tenants</b></button></a>&nbsp; &nbsp;<br/><br/>
	<a href="deletemyownerprofile.jsp"><button><b>Delete My Account</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; &nbsp;
</div>

<%
		}
%>

<%
	if(userType.equals("Tenant")) {
%>
<div class="successMsg"><b><%= user%></b></div><br/><br/>
<div class="tenantmenu">
	<a href="tenantindex.jsp"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="ViewMyInfo"><button><b>View/Edit My Details</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyRentalDetails"><button><b>View My Property Details</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyTenancyAgreement"><button><b>View My Tenancy Agreement</b></button></a>&nbsp; | &nbsp;
	<a href="ViewMyOwnerDetails"><button><b>View My Owner Details</b></button></a>&nbsp; &nbsp;<br/><br/>
	<a href="deletemytenantprofile.jsp"><button><b>Delete My Account</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; &nbsp;
</div>

<%
	}
%>

</body>
</html>