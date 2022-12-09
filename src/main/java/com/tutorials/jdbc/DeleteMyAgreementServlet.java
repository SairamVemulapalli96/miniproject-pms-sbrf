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
import com.tutorials.jdbc.bo.Owner;
import com.tutorials.jdbc.dao.AgreementDAO;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(
		description = "A Servlet to delete the data from the database", 
		urlPatterns = { 
				"/DeleteMyAgreementServlet",
				"/DeleteMyAgreement"
		})
public class DeleteMyAgreementServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyAgreementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("DeleteMyAgreementServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			AgreementDAO.connectToDB();
			System.out.println("DB Connection has been obtained");
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			throw new ServletException(e.getMessage());
		}
		
		int aid =-1;
		String aidStr = request.getParameter("aid");
		
		if(null!=aidStr && aidStr.trim().length()>0) {
			aid = Integer.parseInt(aidStr);
		}
		
		System.out.println("Id Parameter from the Request : " +aid);
		
		int rowsDeleted = AgreementDAO.deleteAgreementByAId(aid);
		String message = "Agreement not deleted sucessfully!";
		String flag = "false";
		
		if(rowsDeleted > 0)  {
			flag = "true";
			message = "Agreement deleted sucessfully!";
		}
		
		request.setAttribute("flag", flag);
		request.setAttribute("message", message);
		
		// 2. Store it in a way where the data is accessible in the JSP
		
		HttpSession session = request.getSession(true);
		Owner owner = (Owner) session.getAttribute("owner");
		int oid = owner.getOId();
		List<Agreement> agreementList = AgreementDAO.getAgreementsByOId(oid);
		System.out.println("agreementList from DAO : " + agreementList);
		request.setAttribute("agreementList", agreementList);
			// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("viewmyagreements.jsp").forward(request, response);
	}
}