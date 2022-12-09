package com.tutorials.jdbc.dao;
import com.tutorials.jdbc.bo.Agreement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AgreementDAO {
	
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
		
		/*List<Agreement> agreementList = agreementListAll();
		System.out.println("agreementList size :  " + agreementList.size());

		if(agreementList.size()>0) {
			for(Agreement agreement : agreementList) {
				System.out.println(agreement);
			}
		}*/
	}
	
	public static void connectToDB() throws Exception
	{	
		Class.forName(JDBC_DRIVER_CLASS);
		conn = DriverManager.getConnection(connURL, userName, password);
	}
	
	public static int createAgreement(Agreement agreement)
	{
		System.out.println("--- createAgreement - agreement :: " + agreement);
		
		String sql = "INSERT INTO AGREEMENTS(OID, OWNER_NAME, PID, PROPERTY_NAME, TID, TENANT_NAME, TENANCY_START_DATE, TENANCY_END_DATE, RENT, MAINTENANCE, TERMS)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
			
			pStmt.setInt(1, agreement.getOId());
			pStmt.setString(2, agreement.getOwnerName());
			pStmt.setInt(3, agreement.getPId());
			pStmt.setString(4, agreement.getPropertyName());
			pStmt.setInt(5, agreement.getTId());
			pStmt.setString(6, agreement.getTenantName());
			pStmt.setString(7, agreement.getTenancyStartDate());
			pStmt.setString(8, agreement.getTenancyEndDate());
			pStmt.setInt(9, agreement.getRent());
			pStmt.setInt(10, agreement.getMaintenance());
			pStmt.setString(11, agreement.getTerms());
			
		
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

	
	public static Agreement getAgreementByTId(int idParam)
	{
		System.out.println("--- getAgreementByTId - idParam :: " + idParam);
		
		String sql = "SELECT * FROM AGREEMENTS WHERE TID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count =0;

		Agreement agreement = null;
		
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
				agreement = new Agreement();

				agreement.setAId(rs.getInt("AID"));
				agreement.setOId(rs.getInt("OID"));
				agreement.setOwnerName(rs.getString("OWNER_NAME"));
				agreement.setPId(rs.getInt("PID"));
				agreement.setPropertyName(rs.getString("PROPERTY_NAME"));
				agreement.setTId(rs.getInt("TID"));
				agreement.setTenantName(rs.getString("TENANT_NAME"));
				agreement.setTenancyStartDate(rs.getString("TENANCY_START_DATE"));
				agreement.setTenancyEndDate(rs.getString("TENANCY_END_DATE"));
				agreement.setRent(rs.getInt("RENT"));
				agreement.setMaintenance(rs.getInt("MAINTENANCE"));
				agreement.setTerms(rs.getString("TERMS"));
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
		
		return agreement;
	}
	
	public static Agreement getAgreementByAId(int idParam)
	{
		System.out.println("--- getAgreementByAId - idParam :: " + idParam);
		
		String sql = "SELECT * FROM AGREEMENTS WHERE AID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count =0;

		Agreement agreement = null;
		
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
				agreement = new Agreement();

				agreement.setAId(rs.getInt("AID"));
				agreement.setOId(rs.getInt("OID"));
				agreement.setOwnerName(rs.getString("OWNER_NAME"));
				agreement.setPId(rs.getInt("PID"));
				agreement.setPropertyName(rs.getString("PROPERTY_NAME"));
				agreement.setTId(rs.getInt("TID"));
				agreement.setTenantName(rs.getString("TENANT_NAME"));
				agreement.setTenancyStartDate(rs.getString("TENANCY_START_DATE"));
				agreement.setTenancyEndDate(rs.getString("TENANCY_END_DATE"));
				agreement.setRent(rs.getInt("RENT"));
				agreement.setMaintenance(rs.getInt("MAINTENANCE"));
				agreement.setTerms(rs.getString("TERMS"));
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
		
		return agreement;
	}

	
	public static List<Agreement> getAgreementsByOId(int oidParam)
	{
		System.out.println("--- getPropertiesByOId - idParam :: " + oidParam);
		
		String sql = "SELECT * FROM AGREEMENTS WHERE OID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Agreement agreement = null;
		List<Agreement> agreementList = new ArrayList<>();
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, oidParam);
			rs = stmt.executeQuery();
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				agreement = new Agreement();

				agreement.setAId(rs.getInt("AID"));
				agreement.setOId(rs.getInt("OID"));
				agreement.setOwnerName(rs.getString("OWNER_NAME"));
				agreement.setPId(rs.getInt("PID"));
				agreement.setPropertyName(rs.getString("PROPERTY_NAME"));
				agreement.setTId(rs.getInt("TID"));
				agreement.setTenantName(rs.getString("TENANT_NAME"));
				agreement.setTenancyStartDate(rs.getString("TENANCY_START_DATE"));
				agreement.setTenancyEndDate(rs.getString("TENANCY_END_DATE"));
				agreement.setRent(rs.getInt("RENT"));
				agreement.setMaintenance(rs.getInt("MAINTENANCE"));
				agreement.setTerms(rs.getString("TERMS"));
				
				agreementList.add(agreement);				
				
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
		
		return agreementList;
		
	}
	
	public static void updateAgreement(Agreement agreement) throws Exception
	{
		System.out.println("--- updateAgreement - agreement :: " + agreement);
		
		String sql = "UPDATE AGREEMENTS SET "
				+ "OID=?, OWNER_NAME=?, PID=?, PROPERTY_NAME=?, TID=?, TENANT_NAME=?, TENANCY_START_DATE=?, TENANCY_END_DATE=?, RENT=?,MAINTENANCE=?,TERMS=?"
				+ " WHERE AID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setInt(1, agreement.getOId());
		pStmt.setString(2, agreement.getOwnerName());
		pStmt.setInt(3, agreement.getPId());
		pStmt.setString(4, agreement.getPropertyName());
		pStmt.setInt(5, agreement.getTId());
		pStmt.setString(6, agreement.getTenantName());
		pStmt.setString(7, agreement.getTenancyStartDate());
		pStmt.setString(8, agreement.getTenancyEndDate());
		pStmt.setInt(9, agreement.getRent());
		pStmt.setInt(10, agreement.getMaintenance());
		pStmt.setString(11, agreement.getTerms());
		pStmt.setInt(12, agreement.getAId());
		
		
		recordsUpdated = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
	public static int deleteAgreementByAId(int idParam)
	{
		System.out.println("--- deleteAgreementByAId - idParam :: " + idParam);
		
		String sql = "DELETE FROM AGREEMENTS WHERE AID=?";

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


