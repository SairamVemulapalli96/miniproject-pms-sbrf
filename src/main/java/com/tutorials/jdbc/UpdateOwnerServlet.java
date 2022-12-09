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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateOwner")
public class UpdateOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOwnerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdateServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Person object
		Owner owner = new Owner();
		
		String idStr = request.getParameter("oid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing OId value, can't update the row!");
		}
		
		int oid = Integer.parseInt(idStr);
		
		String name = request.getParameter("name");
		
		String ageStr = request.getParameter("age");
		int age = ageStr!=null ? Integer.parseInt(ageStr) : 0;
		
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		String phoneno = request.getParameter("phoneno");
		String address = request.getParameter("address");
		
		//2. Prepare the Person Object with the values obtained from Request
		owner.setOId(oid);
		owner.setName(name);
		owner.setAge(age);
		//validate that the gender is not null or empty, 
		//otherwise it will throw a NullPointerException
		owner.setGender(gender.charAt(0));
		owner.setEmail(email);
		//owner.setPassword(password);
		owner.setPhoneno(phoneno);
		owner.setAddress(address);
		
		System.out.println("Person Object prepared from the Request parameters : " + owner);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			OwnerDAO.updateOwner(owner);
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
			request.setAttribute("message", "Record updated successfully!");
		}
		request.getSession().setAttribute("owner", owner);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("editowner.jsp").forward(request, response);
		
		
	}

}
