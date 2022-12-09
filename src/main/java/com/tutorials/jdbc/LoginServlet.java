package com.tutorials.jdbc;

import java.io.IOException;
import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.dao.OwnerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.dao.OwnerDAO;
import com.tutorials.jdbc.bo.Tenant;
import com.tutorials.jdbc.dao.TenantDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "A Servlet to handle the Login Action", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("LoginServlet invoked!");
		
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//out.println("LoginServlet invoked! <br/>");
		
		//1. Get the Email
		String email = request.getParameter("email");
		
		//2. Get the password
		String password = request.getParameter("password");
		
		//2. Get the user type
		String userType = request.getParameter("usertype");
		
		String url = null;
	
		
		if(userType.equals("Admin"))
		{
		//3. Authenticate / validate 
		
		if(email.equals("admin@gmail.com") && password.equals("admin")) {
			
		System.out.println("[INFO] Credentials matched!");
			//out.println("Success!");
			request.setAttribute("message", "Welcome Admin ");
			url = "/index.jsp";
			
			request.getSession().setAttribute("user", email);
			request.getSession().setAttribute("usertype", userType);
			System.out.println(userType);
		} else {
			System.out.println("[ERR] Credentials Mismatch!");
			//out.println("Failure");
			url = "/login.jsp";
			request.setAttribute("errorMessage", "Invalid Credentials. Try again!");
			
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		else if(userType.equals("Owner"))
		{
			try {
				OwnerDAO.connectToDB();
			} catch (Exception e) {
				System.err.println("Exception occurred while connecting to the Database");
				System.err.println("Error Message : " + e.getMessage());
				//TODO: Remove this later, as this is not a good practice
				e.printStackTrace();
				
				//throw new ServletException(e.getMessage());
			}
			
			Owner owner = OwnerDAO.getOwnerByEmail(email);
			System.out.println(owner);
			
			if(null!=owner)
			{
			if(email.equals(owner.getEmail()) && password.equals(owner.getPassword()))
			{
				System.out.println("[INFO] Credentials matched!");
				url = "/ownerindex.jsp";
				request.setAttribute("message", "Welcome " + owner.getName());
				request.getSession().setAttribute("user", owner.getName());	
				request.getSession().setAttribute("owner", owner);
				request.getSession().setAttribute("usertype", userType);
			}
			
			else {
				System.out.println("[ERR] Credentials Mismatch!");
				//out.println("Failure");
				url = "/login.jsp";
				request.setAttribute("errorMessage", "Invalid Credentials. Try again!");
				
			}
			
			}
			else {
				System.out.println("[ERR] Credentials Mismatch!");
				//out.println("Failure");
				url = "/login.jsp";
				request.setAttribute("errorMessage", "No Owner exists with the given Email Id. Try again!");
				
			}
			
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		
		else if(userType.equals("Tenant"))
		{
			try {
				TenantDAO.connectToDB();
			} catch (Exception e) {
				System.err.println("Exception occurred while connecting to the Database");
				System.err.println("Error Message : " + e.getMessage());
				//TODO: Remove this later, as this is not a good practice
				e.printStackTrace();
				
				//throw new ServletException(e.getMessage());
			}
			
			Tenant tenant = TenantDAO.getTenantByEmail(email);
			System.out.println(tenant);
			
			if(null!=tenant)
			{
			if(email.equals(tenant.getEmail()) && password.equals(tenant.getPassword()))
			{
				System.out.println("[INFO] Credentials matched!");
				url = "/tenantindex.jsp";
				request.setAttribute("message", "Welcome" + tenant.getName());
				request.getSession().setAttribute("user", tenant.getName());	
				request.getSession().setAttribute("tenant", tenant);
				request.getSession().setAttribute("usertype", userType);
			}
			
			else {
				System.out.println("[ERR] Credentials Mismatch!");
				//out.println("Failure");
				url = "/login.jsp";
				request.setAttribute("errorMessage", "Invalid Credentials. Try again!");
			}
			
			}
			else {
				System.out.println("[ERR] Credentials Mismatch!");
				//out.println("Failure");
				url = "/login.jsp";
				request.setAttribute("errorMessage", "No Tenant exists with the given Email Id. Try again!");
				
			}
			
			}
			
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			
		}
	

