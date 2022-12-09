package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Property;
import com.tutorials.jdbc.dao.PropertyDAO;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateProperty")
public class CreatePropertyServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePropertyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("CreatePropertyServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("Create Servlet invoked!");*/
		
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
		
		// 2. Collect all the Input data and prepare a Person object
		Property property = new Property();
		
		String property_name = request.getParameter("propertyname");
		
		String door_no = request.getParameter("doorno");
		String street_name = request.getParameter("streetname");
		String city = request.getParameter("city");
		String pincode = request.getParameter("pincode");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String area = request.getParameter("area");
		String facing = request.getParameter("facing");
		String purchase_value = request.getParameter("purchasevalue");
		String flat_no = request.getParameter("flatno");
		String address = request.getParameter("address");
		String property_tax = request.getParameter("propertytax");
		String water_tax = request.getParameter("watertax");
		String electricity_charges = request.getParameter("electricitycharges");
		
		String oidStr = request.getParameter("oid");
		int oid = oidStr!=null ? Integer.parseInt(oidStr) : 0;
		String owner_name = request.getParameter("ownername");

		
		String tidStr = request.getParameter("tid");
		int tid = tidStr!=null ? Integer.parseInt(tidStr) : 0;
		String tenant_name = request.getParameter("tenantname");
	
		property.setPropertyName(property_name);
		property.setDoorNo(door_no);
		property.setStreetName(street_name);
		property.setCity(city);
		property.setPincode(pincode);
		property.setState(state);
		property.setCountry(country);
		property.setArea(area);
		property.setFacing(facing);
		property.setPurchaseValue(purchase_value);
		property.setFlatNo(flat_no);
		property.setAddress(address);
		property.setPropertyTax(property_tax);
		property.setWaterTax(water_tax);
		property.setElectricityCharges(electricity_charges);
		property.setOId(oid);
		property.setOwnerName(owner_name);
		property.setTId(tid);
		property.setTenantName(tenant_name);
	
		
		
		System.out.println("Property Object prepared from the Request parameters : " + property);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = PropertyDAO.createProperty(property);
		}catch(Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while inserting the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
		}
		
		// 4. Prepare the message to be shown to the User
		String message = null;
		String flag = null;
		
		if(lastInsertedId > 0) {
			//message = "Record inserted successfully";
			message = "Record inserted successfully, with the Id : " + lastInsertedId;
			flag = "success";
		} else {
			message = "Records was NOT inserted!";
			request.setAttribute("exceptionMsg", exceptionMsg);
			flag = "failure";
		}
		
		// 5. Store it in a way where the data is accessible in the JSP
		request.setAttribute("message", message);
		request.setAttribute("flag", flag);
	
		request.setAttribute("propertyForm", property);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			property = PropertyDAO.getPropertyById(lastInsertedId);
			request.setAttribute("property", property);
			url = "viewproperty.jsp";
		}else {
			List<Property> propertyList = PropertyDAO.propertyListAll();
			request.setAttribute("propertyList", propertyList);
			//url = "list.jsp";
			url = "createproperty.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
