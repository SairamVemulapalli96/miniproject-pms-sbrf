package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Property;
import com.tutorials.jdbc.dao.PropertyDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewPropertyServlet", "/ViewProperty" })
public class ViewPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPropertyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewPropertyServlet - doGet() invoked");
		
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
		
		int pid =1;
		String pidStr = request.getParameter("pid");
		
		if(null!=pidStr && pidStr.trim().length()>0) {
			pid = Integer.parseInt(pidStr);
		}
		
		System.out.println("Id Parameter from the Request : " + pid);
		
		Property property = PropertyDAO.getPropertyById(pid);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("property", property);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewproperty.jsp").forward(request, response);
	}

}
