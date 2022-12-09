package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.dao.TenantDAO;
import com.tutorials.jdbc.bo.Tenant;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewMyInfoServlet", "/ViewMyInfo" })
public class ViewMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewMyInfoServlet - doGet() invoked");
		
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
		int tid =-1;
		HttpSession session = request.getSession(true);
		Tenant tenantInSession = (Tenant) session.getAttribute("tenant");
		
		tid = tenantInSession.getTId();
		
	
		System.out.println("Id Parameter from the Request : " + tid);
		
		Tenant tenant = TenantDAO.getTenantById(tid);
		
		System.out.println("tenant from DAO : " + tenant);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("tenant", tenant);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		//request.getRequestDispatcher("viewtenant.jsp").forward(request, response);
		request.getRequestDispatcher("viewmyinfo.jsp").forward(request, response);
	}

}
