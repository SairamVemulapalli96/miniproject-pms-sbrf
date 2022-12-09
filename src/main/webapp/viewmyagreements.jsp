<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Agreement" %>
<%@include file="dynamicmenu.jsp" %>
		<h1>List of Your Agreements</h1>
		<%
			List<Agreement> agreementList = new ArrayList<>();
			Object obj = request.getAttribute("agreementList");
			if(null!=obj) {
				agreementList = (List<Agreement>) obj;
			}
		%>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h2>Agreements List </h2>
		Total list of agreements is : <%= agreementList.size() %>
		<pre>
		</pre>
		<%
			if(agreementList.size()<=0) {
		%>
				<div class=errorMsg>Sorry, you don't have any agreements yet.</div>
		<%
			} else {
		%>
		<table border="1">
			<thead>
				<tr>
					<td><b>Agreement Id</b></td>
					<td><b>Owner Name</b></td>
					<td><b>Property Name</b></td>
					<td><b>Tenant Name</b></td>
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Agreement agreement : agreementList)
			{
				out.println("<tr>");
				out.println("<td class='center'><b>" + agreement.getAId() + "</b> " + 
				"<a href='ViewAgreement?aid=" + agreement.getAId() + "'> View/Edit " + "</a>  " +
				"</td>");
				out.println("<td>" + agreement.getOwnerName() + "</td>");
				out.println("<td>" + agreement.getPropertyName() + "</td>");
				out.println("<td>" + agreement.getTenantName() + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
		<%@include file="footer.jsp" %>
