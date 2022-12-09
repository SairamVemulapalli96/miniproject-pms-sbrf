package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.dao.OwnerDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewOwnerServlet", "/ViewOwner" })
public class ViewOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOwnerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewOwnerServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			OwnerDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		int oid =1;
		String idStr = request.getParameter("oid");
		
		if(null!=idStr && idStr.trim().length()>0) {
			oid = Integer.parseInt(idStr);
		}
		
		System.out.println("Id Parameter from the Request : " + oid);
		
		Owner owner = OwnerDAO.getOwnerById(oid);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("owner", owner);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewowner.jsp").forward(request, response);
	}

}
