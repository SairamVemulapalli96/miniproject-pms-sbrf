package com.tutorials.jdbc.dao;
import com.tutorials.jdbc.bo.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {
	static Connection conn = null;
	static String connURL1 = "jdbc:mysql://localhost:3306/PropertyManagementSystem?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
	static String connURL = "jdbc:mysql://localhost:3306/PropertyManagementSystem";
    static String userName = "root";
	static String password = "root";
 	//static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	static String JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	
	
	public static void main(String... args)
	{
		try {
			connectToDB();
			System.out.println("Connection to the MySQL DB is successful!");
			System.out.println("conn : " + conn);
		}catch(Exception exception) {
			System.err.println("Exception occurred while making a connection..");
			System.err.println("Error Message :  " + exception.getMessage());
			exception.printStackTrace();//NOT recommended in PROD codebase, as it reveals the code structure
		}
		
		List<Property> propertyList = propertyListAll();
		System.out.println("propertyList size :  " + propertyList.size());

		if(propertyList.size()>0) {
			for(Property property : propertyList) {
				System.out.println(property);
			}
		}
	}
	
	public static void connectToDB() throws Exception
	{	
		Class.forName(JDBC_DRIVER_CLASS);
		conn = DriverManager.getConnection(connURL, userName, password);
	}
	
	
	
	public static List<Property> propertyListAll()
	{
		System.out.println("--- propertyListAll invoked --- ");
		
		String sql = "SELECT * FROM PROPERTIES";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Property> propertyList = new ArrayList<>();
		Property property = null;
		
		System.out.println("Connection Object : " + conn);

		try {
			
			if(null==conn || conn.isClosed())
			{
				System.out.println("Connection is null, creating a new one");
				connectToDB();
				System.out.println("Connection Object : " + conn);
			}
			
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				//create a new instance of Property whenever there is a data from the ResultSet
				property = new Property();
				
				property.setPId(rs.getInt("PID"));
				property.setPropertyName(rs.getString("PROPERTY_NAME"));
				property.setDoorNo(rs.getString("DOOR_NO"));
				property.setStreetName(rs.getString("STREET_NAME"));
				property.setCity(rs.getString("CITY"));
				property.setPincode(rs.getString("PINCODE"));
				property.setState(rs.getString("STATE"));
				property.setCountry(rs.getString("COUNTRY"));
				property.setArea(rs.getString("AREA"));
				property.setFacing(rs.getString("FACING"));
				property.setPurchaseValue(rs.getString("PURCHASE_VALUE"));
				property.setFlatNo(rs.getString("FLAT_NO"));
				property.setAddress(rs.getString("ADDRESS"));
				property.setPropertyTax(rs.getString("PROPERTY_TAX"));
				property.setWaterTax(rs.getString("WATER_TAX"));
				property.setElectricityCharges(rs.getString("ELECTRICITY_CHARGES"));
				property.setOId(rs.getInt("OID"));
				property.setOwnerName(rs.getString("OWNER_NAME"));
				property.setTId(rs.getInt("TID"));
				property.setTenantName(rs.getString("TENANT_NAME"));
				
				//Add the property object into the Collection
				propertyList.add(property);
			}
		}catch(SQLException sqlEx) {
			System.err.println("SQLException occurred while fetching the rows from the table");
			System.err.println("Message : " + sqlEx.getMessage());
			sqlEx.printStackTrace();
		}catch(Exception ex) {
			System.err.println("Exception occurred while fetching the rows from the table");
			System.err.println("Message : " + ex.getMessage());
			ex.printStackTrace();
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while closing the JDBC Resources");
				System.err.println("Message : " + sqlException.getMessage());
			}
		}

		return propertyList;
	}	
	
	public static List<Property> getPropertiesByOId(int idParam)
	{
		System.out.println("--- getPropertiesByOId - idParam :: " + idParam);
		
		String sql = "SELECT * FROM PROPERTIES WHERE OID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Property property = null;
		List<Property> propertyList = new ArrayList<>();
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				property = new Property();

				property.setPId(rs.getInt("PID"));
				property.setPropertyName(rs.getString("PROPERTY_NAME"));
				property.setDoorNo(rs.getString("DOOR_NO"));
				property.setStreetName(rs.getString("STREET_NAME"));
				property.setCity(rs.getString("CITY"));
				property.setPincode(rs.getString("PINCODE"));
				property.setState(rs.getString("STATE"));
				property.setCountry(rs.getString("COUNTRY"));
				property.setArea(rs.getString("AREA"));
				property.setFacing(rs.getString("FACING"));
				property.setPurchaseValue(rs.getString("PURCHASE_VALUE"));
				property.setFlatNo(rs.getString("FLAT_NO"));
				property.setAddress(rs.getString("ADDRESS"));
				property.setPropertyTax(rs.getString("PROPERTY_TAX"));
				property.setWaterTax(rs.getString("WATER_TAX"));
				property.setElectricityCharges(rs.getString("ELECTRICITY_CHARGES"));
				property.setOId(rs.getInt("OID"));
				property.setOwnerName(rs.getString("OWNER_NAME"));
				property.setTId(rs.getInt("TID"));
				property.setTenantName(rs.getString("TENANT_NAME"));
				
				propertyList.add(property);				
				
			}
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while reading the data from the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=rs) rs.close();
					if(null!=stmt) stmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		
		return propertyList;
	}
	
	public static Property getPropertyByTId(int idParam)
	{
		System.out.println("--- getPropertiesByOId - idParam :: " + idParam);
		
		String sql = "SELECT * FROM PROPERTIES WHERE TID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count =0;

		Property property = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				property = new Property();

				property.setPId(rs.getInt("PID"));
				property.setPropertyName(rs.getString("PROPERTY_NAME"));
				property.setDoorNo(rs.getString("DOOR_NO"));
				property.setStreetName(rs.getString("STREET_NAME"));
				property.setCity(rs.getString("CITY"));
				property.setPincode(rs.getString("PINCODE"));
				property.setState(rs.getString("STATE"));
				property.setCountry(rs.getString("COUNTRY"));
				property.setArea(rs.getString("AREA"));
				property.setFacing(rs.getString("FACING"));
				property.setPurchaseValue(rs.getString("PURCHASE_VALUE"));
				property.setFlatNo(rs.getString("FLAT_NO"));
				property.setAddress(rs.getString("ADDRESS"));
				property.setPropertyTax(rs.getString("PROPERTY_TAX"));
				property.setWaterTax(rs.getString("WATER_TAX"));
				property.setElectricityCharges(rs.getString("ELECTRICITY_CHARGES"));
				property.setOId(rs.getInt("OID"));
				property.setOwnerName(rs.getString("OWNER_NAME"));
				property.setTId(rs.getInt("TID"));
				property.setTenantName(rs.getString("TENANT_NAME"));
				
				count++;				
				
			}
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while reading the data from the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=rs) rs.close();
					if(null!=stmt) stmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		
		if(count==0) {
			System.out.println("There are no matching records for the criteria specified!");
		}else {
			System.out.println("Data read from the table successfully!");
		}
		
		return property;
	}

	public static Property getPropertyById(int idParam)
	{
		System.out.println("--- getTenantById - idParam :: " + idParam);
		
		String sql = "SELECT * FROM PROPERTIES WHERE PID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Property property = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				property = new Property();

				property.setPId(rs.getInt("PID"));
				property.setPropertyName(rs.getString("PROPERTY_NAME"));
				property.setDoorNo(rs.getString("DOOR_NO"));
				property.setStreetName(rs.getString("STREET_NAME"));
				property.setCity(rs.getString("CITY"));
				property.setPincode(rs.getString("PINCODE"));
				property.setState(rs.getString("STATE"));
				property.setCountry(rs.getString("COUNTRY"));
				property.setArea(rs.getString("AREA"));
				property.setFacing(rs.getString("FACING"));
				property.setPurchaseValue(rs.getString("PURCHASE_VALUE"));
				property.setFlatNo(rs.getString("FLAT_NO"));
				property.setAddress(rs.getString("ADDRESS"));
				property.setPropertyTax(rs.getString("PROPERTY_TAX"));
				property.setWaterTax(rs.getString("WATER_TAX"));
				property.setElectricityCharges(rs.getString("ELECTRICITY_CHARGES"));
				property.setOId(rs.getInt("OID"));
				property.setOwnerName(rs.getString("OWNER_NAME"));
				property.setTId(rs.getInt("TID"));
				property.setTenantName(rs.getString("TENANT_NAME"));
				
				count++;
			}
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while reading the data from the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=rs) rs.close();
					if(null!=stmt) stmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		if(count==0) {
			System.out.println("There are no matching records for the criteria specified!");
		}else {
			System.out.println("Data read from the table successfully!");
		}
		return property;
	}

	
	public static int createProperty(Property property)
	{
		System.out.println("--- createProperty - property :: " + property);
		
		String sql = "INSERT INTO PROPERTIES(PROPERTY_NAME, DOOR_NO, STREET_NAME, CITY, PINCODE, STATE, COUNTRY, AREA, FACING, PURCHASE_VALUE, FLAT_NO, ADDRESS, PROPERTY_TAX, WATER_TAX, ELECTRICITY_CHARGES, OID, OWNER_NAME, TID, TENANT_NAME)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int lastInsertedId=0, recordsInserted = 0;
		
		try {
			
			if(null==conn)
			{
				connectToDB();
			}
			
			System.out.println("AutoCommit ? : " + conn.getAutoCommit());
			/*conn.setAutoCommit(true);
			System.out.println("(2) AutoCommit ? : " + conn.getAutoCommit());*/
			
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, property.getPropertyName());
			pStmt.setString(2, property.getDoorNo());
			pStmt.setString(3, property.getStreetName());
			pStmt.setString(4, property.getCity());
			pStmt.setString(5, property.getPincode());
			pStmt.setString(6, property.getState());
			pStmt.setString(7, property.getCountry());
			pStmt.setString(8, property.getArea());
			pStmt.setString(9, property.getFacing());
			pStmt.setString(10, property.getPurchaseValue());
			pStmt.setString(11, property.getFlatNo());
			pStmt.setString(12, property.getAddress());
			pStmt.setString(13, property.getPropertyTax());
			pStmt.setString(14, property.getWaterTax());
			pStmt.setString(15, property.getElectricityCharges());
			pStmt.setInt(16, property.getOId());
			pStmt.setString(17, property.getOwnerName());
			pStmt.setInt(18, property.getTId());
			pStmt.setString(19, property.getTenantName());
			
			
			recordsInserted = pStmt.executeUpdate();
			//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println("recordsInserted : " + recordsInserted);
			
		    rs = pStmt.executeQuery("SELECT LAST_INSERT_ID()");

		    if (rs.next()) {
		    	lastInsertedId = rs.getInt(1);
		    } else {
		        System.err.println("There was no record inserted in this session!");
		    }

		    System.out.println("Key returned from " +
		                       "'SELECT LAST_INSERT_ID()': " +
		                       lastInsertedId);
			
		}catch(SQLException sqlException) {
			System.err.println("SQL Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		} finally {
			try {
				if(null!=pStmt) pStmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while inserting the data into the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=pStmt) pStmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		
		System.out.println("Records Inserted with the Id : " + lastInsertedId);
		//System.out.println("Records Inserted  : " + recordsInserted);
		
		return lastInsertedId;
	}
	
	public static void updateProperty(Property property) throws Exception
	{
		System.out.println("--- updateProperty - property :: " + property);
		
		String sql = "UPDATE PROPERTIES SET "
				+ "PROPERTY_NAME=?, DOOR_NO=?, STREET_NAME=?, CITY=?, PINCODE=?, STATE=?, COUNTRY=?, AREA=?, FACING=?,PURCHASE_VALUE=?,FLAT_NO=?,ADDRESS=?,PROPERTY_TAX=?,WATER_TAX=?, ELECTRICITY_CHARGES=?,OID=?,OWNER_NAME=?,TID=?,TENANT_NAME=? "
				+ " WHERE PID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1, property.getPropertyName());
		pStmt.setString(2, property.getDoorNo());
		pStmt.setString(3, property.getStreetName());
		pStmt.setString(4, property.getCity());
		pStmt.setString(5, property.getPincode());
		pStmt.setString(6, property.getState());
		pStmt.setString(7, property.getCountry());
		pStmt.setString(8, property.getArea());
		pStmt.setString(9, property.getFacing());
		pStmt.setString(10, property.getPurchaseValue());
		pStmt.setString(11, property.getFlatNo());
		pStmt.setString(12, property.getAddress());
		pStmt.setString(13, property.getPropertyTax());
		pStmt.setString(14, property.getWaterTax());
		pStmt.setString(15, property.getElectricityCharges());
		pStmt.setInt(16, property.getOId());
		pStmt.setString(17, property.getOwnerName());
		pStmt.setInt(18, property.getTId());
		pStmt.setString(19, property.getTenantName());
		pStmt.setInt(20,property.getPId());
		
		recordsUpdated = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
	public static int deletePropertyByPId(int idParam)
	{
		System.out.println("--- deleteOwnerByOId - idParam :: " + idParam);
		
		String sql = "DELETE FROM PROPERTIES WHERE PID=?";

		System.out.println("SQL Query :: " + sql);

		PreparedStatement pStmt = null;
		int rowsDeleted = 0;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, idParam);
		
			rowsDeleted = pStmt.executeUpdate();
			
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while deleting the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while deleting the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=pStmt) pStmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while closing the JDBC Resources");
				System.err.println("Message : " + sqlException.getMessage());
			}
		}
		if(rowsDeleted==0) {
			System.out.println("There are no records deleted");
		}else {
			System.out.println("Record deleted from the table successfully!");
		}

		return rowsDeleted;
	}



	
}
