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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateTenant")
public class UpdateTenantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTenantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdateTenantServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Person object
		Tenant tenant = new Tenant();
		
		String idStr = request.getParameter("tid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing TId value, can't update the row!");
		}
		
		
		int tid = Integer.parseInt(idStr);
		
		String name = request.getParameter("name");
		
		String ageStr = request.getParameter("age");
		int age = ageStr!=null ? Integer.parseInt(ageStr) : 0;
		
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		String phoneno = request.getParameter("phoneno");
		String address = request.getParameter("address");
		
		String oidStr = request.getParameter("oid");
		if(null==oidStr) {
			//TODO Revisit this later
			throw new ServletException("Missing OId value, can't update the row!");
		}
		
		int oid = Integer.parseInt(oidStr);
		
		//2. Prepare the Tenant Object with the values obtained from Request
		tenant.setTId(tid);
		tenant.setName(name);
		tenant.setAge(age);
		//validate that the gender is not null or empty, 
		//otherwise it will throw a NullPointerException
		tenant.setGender(gender.charAt(0));
		tenant.setEmail(email);
		//tenant.setPassword(password);
		tenant.setPhoneno(phoneno);
		tenant.setAddress(address);
		tenant.setOId(oid);
		
		System.out.println("Tenant Object prepared from the Request parameters : " + tenant);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			TenantDAO.updateTenant(tenant);
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
		request.getSession().setAttribute("tenant", tenant);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("edittenant.jsp").forward(request, response);
		
		
	}

}
