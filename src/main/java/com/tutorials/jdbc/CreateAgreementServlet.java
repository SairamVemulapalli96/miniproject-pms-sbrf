package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Agreement;
import com.tutorials.jdbc.dao.AgreementDAO;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateAgreement")
public class CreateAgreementServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAgreementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("CreateAgreementServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("Create Servlet invoked!");*/
		
		// 1. Get the data from Database 
		try {
			AgreementDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Agreement agreement = new Agreement();
		
		String oidStr = request.getParameter("oid");
		int oid = oidStr!=null ? Integer.parseInt(oidStr) : 0;
		String owner_name = request.getParameter("ownername");
		
		String pidStr = request.getParameter("pid");
		int pid = pidStr!=null ? Integer.parseInt(pidStr) : 0;
		
		String property_name = request.getParameter("propertyname");
		
		String tidStr = request.getParameter("tid");
		int tid = tidStr!=null ? Integer.parseInt(tidStr) : 0;
		String tenant_name = request.getParameter("tenantname");
		
		
		String tenancy_start_date = request.getParameter("startdate");
		String tenancy_end_date = request.getParameter("enddate");
		
		String rentStr = request.getParameter("rent");
		int rent = rentStr!=null ? Integer.parseInt(rentStr) : 0;
		
		String maintenanceStr = request.getParameter("maintenance");
		int maintenance = tidStr!=null ? Integer.parseInt(maintenanceStr) : 0;
		
		
		String terms = request.getParameter("terms");
		
		
		
		agreement.setOId(oid);
		agreement.setOwnerName(owner_name);
		agreement.setPId(pid);
		agreement.setPropertyName(property_name);
		agreement.setTId(tid);
		agreement.setTenantName(tenant_name);
		agreement.setTenancyStartDate(tenancy_start_date);
		agreement.setTenancyEndDate(tenancy_end_date);
		agreement.setRent(rent);
		agreement.setMaintenance(maintenance);
		agreement.setTerms(terms);
		
		

		System.out.println("Agreement Object prepared from the Request parameters : " + agreement);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = AgreementDAO.createAgreement(agreement);
		}catch(Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while inserting the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
		}
		
		// 4. Prepare the message to be shown to the User
		String message = null;
		String flag = null;
		
		if(lastInsertedId > 0) {
			//message = "Record inserted successfully";
			message = "Agreement added successfully, with the Id : " + lastInsertedId;
			flag = "success";
		} else {
			message = "Agreement not added!";
			request.setAttribute("exceptionMsg", exceptionMsg);
			flag = "failure";
		}
		
		// 5. Store it in a way where the data is accessible in the JSP
		request.setAttribute("message", message);
		request.setAttribute("flag", flag);
	
		request.setAttribute("agreementForm", agreement);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			agreement = AgreementDAO.getAgreementByAId(lastInsertedId);
			request.setAttribute("agreement", agreement);
			url = "viewagreement.jsp";
		}else {
			//List<Property> propertyList = PropertyDAO.propertyListAll();
			//request.setAttribute("propertyList", propertyList);
			//url = "list.jsp";
			url = "createagreement.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
