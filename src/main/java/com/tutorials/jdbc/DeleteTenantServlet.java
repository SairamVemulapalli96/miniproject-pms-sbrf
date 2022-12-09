package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Tenant;
import com.tutorials.jdbc.dao.TenantDAO;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(
		description = "A Servlet to delete the data from the database", 
		urlPatterns = { 
				"/DeleteTenantServlet",
				"/DeleteTenant"
		})
public class DeleteTenantServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTenantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("DeleteTenantServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			TenantDAO.connectToDB();
			System.out.println("DB Connection has been obtained");
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			throw new ServletException(e.getMessage());
		}
		
		int tid =1;
		String idStr = request.getParameter("tid");
		
		if(null!=idStr && idStr.trim().length()>0) {
			tid = Integer.parseInt(idStr);
		}
		
		System.out.println("Id Parameter from the Request : " + tid);
		
		int rowsDeleted = TenantDAO.deleteTenantByTId(tid);
		String message = "Record not deleted sucessfully!";
		String flag = "false";
		
		if(rowsDeleted > 0)  {
			flag = "true";
			message = "Record deleted sucessfully!";
		}
		
		
		List<Tenant> tenantList = TenantDAO.tenantListAll();
		
		System.out.println("tenantList from DAO : " + tenantList);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("tenantList", tenantList);
		request.setAttribute("flag", flag);
		request.setAttribute("message", message);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("tenantlist.jsp").forward(request, response);
	}
}