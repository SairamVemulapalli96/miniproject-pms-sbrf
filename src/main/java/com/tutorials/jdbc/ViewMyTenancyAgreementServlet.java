package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.bo.Agreement;
import com.tutorials.jdbc.bo.Tenant;
import com.tutorials.jdbc.dao.AgreementDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewMyTenancyAgreementServlet", "/ViewMyTenancyAgreement" })
public class ViewMyTenancyAgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyTenancyAgreementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewMyTenancyAgreementServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			AgreementDAO.connectToDB();
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
		
		
		
		System.out.println("Id Parameter from the Request : " + tid);
		
		Agreement agreement = AgreementDAO.getAgreementByTId(tid);
		
		System.out.println("agreement from DAO : " + agreement);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("agreement", agreement);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewmytenancyagreement.jsp").forward(request, response);
	}

}
