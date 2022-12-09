package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Agreement;
import com.tutorials.jdbc.dao.AgreementDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewAgreementServlet", "/ViewAgreement" })
public class ViewAgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAgreementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ViewAgreementServlet - doGet() invoked");
		
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
		
		int aid =-1;
		String aidStr = request.getParameter("aid");
		
		if(null!=aidStr && aidStr.trim().length()>0) {
			aid = Integer.parseInt(aidStr);
		}
		
		System.out.println("Id Parameter from the Request : " + aid);
		
		Agreement agreement = AgreementDAO.getAgreementByAId(aid);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("agreement", agreement);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewagreement.jsp").forward(request, response);
	}

}
