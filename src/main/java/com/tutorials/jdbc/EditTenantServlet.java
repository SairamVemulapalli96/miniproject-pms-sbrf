package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Tenant;
import com.tutorials.jdbc.dao.TenantDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/EditTenant" })
public class EditTenantServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTenantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("EditTenantServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			TenantDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		int tid =1;
		String tidStr = request.getParameter("tid");
		
		if(null!=tidStr && tidStr.trim().length()>0) {
			tid = Integer.parseInt(tidStr);
		}
		
		System.out.println("Id Parameter from the Request : " + tid);
		
		Tenant tenant = TenantDAO.getTenantById(tid);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("tenant", tenant);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("edittenant.jsp").forward(request, response);
	}

}
