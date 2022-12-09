package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.bo.Tenant;
import com.tutorials.jdbc.bo.TenantSuggestion;
import com.tutorials.jdbc.dao.TenantDAO;
import com.tutorials.jdbc.dao.TenantSuggestionDAO;

@WebServlet(
		description = "A Servlet to list the data from the database", 
		urlPatterns = { 
				"/DeleteMyTenantProfileServlet",
				"/DeletMyTenantProfile"
		})
public class DeleteMyTenantProfileServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyTenantProfileServlet() {
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
		System.out.println("DeleteMyTenantProfileServlet invoked!");
		
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//out.println("LoginServlet invoked! <br/>");
		
		//1. Get the Email
		String suggestions = request.getParameter("suggestions");
		
		//2. Get the password
		String decision = request.getParameter("decision");
		
		HttpSession session = request.getSession(true);
		Tenant tenant = (Tenant) session.getAttribute("tenant");
		String email = tenant.getEmail();
		String tenant_name = tenant.getName();
		
		try {
			TenantSuggestionDAO.connectToDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TenantSuggestion tenantsuggestion = new TenantSuggestion();
		tenantsuggestion.setEmail(email);
		tenantsuggestion.setTenantName(tenant_name);
		tenantsuggestion.setSuggestions(suggestions);
		
		int recordsInserted = TenantSuggestionDAO.createTenantSuggestion(tenantsuggestion);
				
		String url = null;
	
		
		if(decision.equals("Yes"))
		{
			//3. Authenticate / validate 
			int tid =-1;
			tid = tenant.getTId();
			int rowsDeleted = TenantDAO.deleteTenantByTId(tid);
			System.out.println(rowsDeleted);
			String message = "Record not deleted sucessfully!";
			String flag = "false";
			
			if(rowsDeleted > 0)  {
				flag = "true";
				message = "Account deleted sucessfully! We would like to have you back soon";
				System.out.println(message);
			}
			//out.println("Success!");
			String user = (String) session.getAttribute("user");
		
			
			if(user!=null && tenant!=null) {
				session.removeAttribute("user");
				session.removeAttribute("owner");			
			} else {
				request.setAttribute("errorMessage", "Looks like an unauthorized access to this page!");
			}
			request.setAttribute("message", message);
			System.out.println(message);
			url = "/login.jsp";
			request.getServletContext().getRequestDispatcher(url).forward(request, response);
		
		}
		
		else
		{
			if(recordsInserted>0)
			{
				request.setAttribute("message", "We have received your suggestions/concerns and would try to address them as early as possible");
			}
			else
			{
				request.setAttribute("message", "Error occured while receiving your suggestions/concerns. Please Try again");
			}
			url = "/tenantindex.jsp";
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
	}
				
}
