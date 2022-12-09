package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.bo.OwnerSuggestion;
import com.tutorials.jdbc.dao.OwnerDAO;
import com.tutorials.jdbc.dao.OwnerSuggestionDAO;

@WebServlet(
		description = "A Servlet to list the data from the database", 
		urlPatterns = { 
				"/DeletMyOwnerProfileServlet",
				"/DeletMyOwnerProfile"
		})
public class DeleteMyOwnerProfileServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyOwnerProfileServlet() {
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
		System.out.println("DeletMyOwnerProfileServlet invoked!");
		
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//out.println("LoginServlet invoked! <br/>");
		
		//1. Get the Email
		String suggestions = request.getParameter("suggestions");
		
		//2. Get the password
		String decision = request.getParameter("decision");
		
		HttpSession session = request.getSession(true);
		Owner owner = (Owner) session.getAttribute("owner");
		String email = owner.getEmail();
		String owner_name = owner.getName();
		
		try {
			OwnerSuggestionDAO.connectToDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OwnerSuggestion ownersuggestion = new OwnerSuggestion();
		ownersuggestion.setEmail(email);
		ownersuggestion.setOwnerName(owner_name);
		ownersuggestion.setSuggestions(suggestions);
		
		int recordsInserted = OwnerSuggestionDAO.createOwnerSuggestion(ownersuggestion);

		String url = null;
	
		if(decision.equals("Yes"))
		{
			//3. Authenticate / validate 
			int oid =-1;
			oid = owner.getOId();
			int rowsDeleted = OwnerDAO.deleteOwnerByOId(oid);
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
		
			
			if(user!=null && owner!=null) {
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
			url = "/ownerindex.jsp";
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
	}
				
}

