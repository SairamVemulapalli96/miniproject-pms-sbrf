package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Agreement;
import com.tutorials.jdbc.dao.AgreementDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateAgreement")
public class UpdateAgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAgreementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdateAgreementServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Property object
		Agreement agreement = new Agreement();
		
		String aidStr = request.getParameter("aid");
		int aid = aidStr!=null ? Integer.parseInt(aidStr) : 0;
		
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
		
		
		agreement.setAId(aid);
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

			
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			AgreementDAO.updateAgreement(agreement);
		} catch (Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while updating the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
			//TODO Remove later as it is not a good practice
			exception.printStackTrace();
		}
		
		//4. Prepare the response message  
		if(null!=exceptionMsg) {
			request.setAttribute("exceptionMsg", exceptionMsg);
		} else {
			request.setAttribute("message", "Agreement updated successfully!");
		}
		request.getSession().setAttribute("agreement", agreement);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("editagreement.jsp").forward(request, response);
		
		
	}

}
