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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateProperty")
public class UpdatePropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePropertyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdatePropertyServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Property object
		Property property = new Property();
		
		String pidStr = request.getParameter("pid");
		if(null==pidStr) {
			//TODO Revisit this later
			throw new ServletException("Missing PId value, can't update the row!");
		}
		
		
		int pid = Integer.parseInt(pidStr);
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
		if(null==oidStr) {
			//TODO Revisit this later
			throw new ServletException("Missing OId value, can't update the row!");
		}
		
		int oid = Integer.parseInt(oidStr);
		
		String owner_name = request.getParameter("ownername");
		
		String tidStr = request.getParameter("tid");
		if(null==tidStr) {
			//TODO Revisit this later
			throw new ServletException("Missing TId value, can't update the row!");
		}
		
		int tid = Integer.parseInt(tidStr);
		
		String tenant_name = request.getParameter("tenantname");
		
		property.setPId(pid);
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
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			PropertyDAO.updateProperty(property);
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
		request.getSession().setAttribute("property", property);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("editproperty.jsp").forward(request, response);
		
		
	}

}
