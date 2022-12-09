package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.bo.Property;
import com.tutorials.jdbc.dao.PropertyDAO;
import com.tutorials.jdbc.dao.TenantDAO;
import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.dao.OwnerDAO;
import com.tutorials.jdbc.bo.Tenant;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewMyOwnerDetailsServlet", "/ViewMyOwnerDetails" })
public class ViewMyOwnerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyOwnerDetailsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewMyOwnerDetailsServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			PropertyDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		int tid =-1;
		HttpSession session = request.getSession(true);
		Tenant tenant = (Tenant) session.getAttribute("tenant");
		tid = tenant.getTId();
		System.out.println("Tenant Id Parameter from the Request : " + tid);
		
		//Property property = PropertyDAO.getPropertyByTId(tid);
		//System.out.println("property from DAO : " + property);
		//int oid = property.getOId();
		
		/* ---BUG---
		 * In the above approach, since there was no relation of tenant(no foreign key of tenant)
		 * in the owners table, the owner object was fetched from the properties table
		 * as both oid and tid are foreign keys in the properties table. But doing this throws a 
		 * null pointer exception, if the owner/admin has not yet added an entry into properties table
		 * by the time tenant registers into the system and that tenant clicks on view my owner details.
		 * 
		 * --- BUG FIX---
		 * Since we already have the tenant object in the session, we are fetching the oid from the 
		 * tenants table using the tid of tenant object stored in the session, so even if the entry
		 *  was not yet entered in the properties table, tenant would still get his owner details.
		 *
		 */
		
		int oid = tenant.getOId();
		
		Owner owner = OwnerDAO.getOwnerById(oid);
		
		System.out.println("owner from DAO " + owner);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("myowner", owner);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewmyownerdetails.jsp").forward(request, response);
	}

}
