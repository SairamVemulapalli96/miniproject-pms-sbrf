<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test JSP Page</title>
<link rel="stylesheet" href="style.css"/>
<style>

#button ul {
  list-style-type: none;
  /* Remove the bullets from the list */
}



#button ul li.item {
  display: none;
  /* By default, do not display the items (which contains the links) */
}

#button ul:hover .item {
  /* When the user hovers over the button (or any of the links) */
  display: block;
  
 
}
</style>
</head>
<body>
<h1>Dynamic Menu Test</h1>
		<%
			//String message = (String) request.getAttribute("message");
			String userType = (String) session.getAttribute("usertype");
			String user = (String) session.getAttribute("user");
		
			if(userType.equals("Admin")) {
		%>			
				<div class="successMsg"><b>Hello <%= userType%></b></div><br/>
				<div class="ownermenu">
	<a href="/PropertyManagementSystem"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="createowner.jsp"><button><b>Add Owner</b></button></a>&nbsp; | &nbsp;
	<a href="List"><button><b>View All Owners</b></button></a>&nbsp; | &nbsp;
	<a href="searchownerbyoid.jsp"><button><b>View Owner</b></button></a><br/><br/>
	
	 <div id="button">
    <ul>
      <li class="top">Owners</li>
      <li class="item"><a href="createowner.jsp">Add Owner</a></li>
      <li class="item"><a href="List">View All Owners</a></li>
      <li class="item"><a href="searchownerbyoid.jsp">View Owner</a></li>

    </ul>
  </div>
	
	
	
	
	
	
	
	<a href="createtenant.jsp"><button><b>Add Tenant</b></button></a>&nbsp; | &nbsp;
	<a href="TenantList"><button><b>View All Tenants</b></button></a>&nbsp; | &nbsp;
	<a href="searchtenantbyoid.jsp"><button><b>View Tenants of an Owner</b></button></a>&nbsp; | &nbsp;
	<a href="searchtenantbytid.jsp"><button><b>View Tenant</b></button></a><br/><br/>
	
	<a href="PropertyList"><button><b>View All Properties</b></button></a>&nbsp; | &nbsp;
	<a href="createproperty.jsp"><button><b>Add Property</b></button></a>&nbsp; | &nbsp;
	<a href="searchpropertybypid.jsp"><button><b>View Property</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; | &nbsp;
	
</div>
				
		<%		
			}
		%>
		
		<%
		if(userType.equals("Owner")) {
		%>
		<div class="successMsg"><b>Hello <%= user%></b></div><br/>
		<div class="ownermenu">
	<a href="ownerindex.jsp"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="viewmyproperties.jsp"><button><b>View My Properties</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; | &nbsp;
</div>

<%
		}
%>

<%
	if(userType.equals("Tenant")) {
%>
<div class="successMsg"><b>Hello <%= user%></b></div><br/><br/>
<div class="tenantmenu">
	<a href="tenantindex.jsp"><button><b>Home</b></button></a> &nbsp; | &nbsp;
	<a href="viewmytenancyagreement.jsp"><button><b>View My Tenancy Agreement</b></button></a>&nbsp; | &nbsp;
	<a href="Logout"><button><b>Logout</b></button></a>&nbsp; | &nbsp;
</div>

<%
	}
%>
		
</body>
</html>