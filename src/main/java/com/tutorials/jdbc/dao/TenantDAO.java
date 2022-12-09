package com.tutorials.jdbc.dao;
import com.tutorials.jdbc.bo.Tenant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TenantDAO
{
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

		List<Tenant> tenantList = tenantListAll();
		System.out.println("tenantList size :  " + tenantList.size());

		if(tenantList.size()>0) {
			for(Tenant tenant : tenantList) {
				System.out.println(tenant);
			}
		}
	}

	public static void connectToDB() throws Exception
	{	
		Class.forName(JDBC_DRIVER_CLASS);
		conn = DriverManager.getConnection(connURL, userName, password);
	}

	/*public static void unUsedCreateTable() throws SQLException
	{
		String SQL = "CREATE TABLE TEST(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR2(20) NOT NULL, AGE INT NOT NULL, CITY VARCHAR2(20) NOT NULL, REMARKS VARCHAR(20))";
		System.out.println(SQL);
	}*/
	
	public static List<Tenant> tenantListAll()
	{
		System.out.println("--- tenantListAll invoked --- ");
		
		String sql = "SELECT * FROM TENANTS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Tenant> tenantList = new ArrayList<>();
		Tenant tenant = null;
		
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
				//create a new instance of Tenant whenever there is a data from the ResultSet
				tenant = new Tenant();
				
				tenant.setTId(rs.getInt("TId"));//tenant.setId(rs.getInt(1));
				tenant.setName(rs.getString("Name"));
				tenant.setAge(rs.getInt("Age"));
				tenant.setGender(rs.getString("Gender").charAt(0));
				tenant.setEmail(rs.getString("Email"));
				tenant.setPassword(rs.getString("Password"));
				tenant.setPhoneno(rs.getString("Phoneno"));
				tenant.setAddress(rs.getString("Address"));
				tenant.setOId(rs.getInt("OId"));

				//Add the tenant object into the Collection
				tenantList.add(tenant);
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

		

		return tenantList;
	}	
	
	public static Tenant getTenantById(int idParam)
	{
		System.out.println("--- getTenantById - idParam :: " + idParam);
		
		String sql = "SELECT * FROM TENANTS WHERE TID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Tenant tenant = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
			int tid, age, oid;
			String name, email, password, phoneno, address;
			char gender;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				tenant = new Tenant();

				tid = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				gender = rs.getString(4).charAt(0);
				email = rs.getString(5);
				password = rs.getString(6);
				phoneno = rs.getString(7);
				address = rs.getString(8);
				oid = rs.getInt(9);


				tenant.setTId(tid);
				tenant.setName(name);
				tenant.setAge(age);
				tenant.setGender(gender);
				tenant.setEmail(email);
				tenant.setPassword(password);
				tenant.setPhoneno(phoneno);
				tenant.setAddress(address);
				tenant.setOId(oid);

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
		return tenant;
	}
	
	public static Tenant getTenantByEmail(String emailParam)
	{
		System.out.println("--- getTenantById - idParam :: " + emailParam);
		
		String sql = "SELECT * FROM TENANTS WHERE EMAIL=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Tenant tenant = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, emailParam);

			rs = stmt.executeQuery();
		
			int tid, age, oid;
			String name, email, password, phoneno, address;
			char gender;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				tenant = new Tenant();

				tid = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				gender = rs.getString(4).charAt(0);
				email = rs.getString(5);
				password = rs.getString(6);
				phoneno = rs.getString(7);
				address = rs.getString(8);
				oid = rs.getInt(9);


				tenant.setTId(tid);
				tenant.setName(name);
				tenant.setAge(age);
				tenant.setGender(gender);
				tenant.setEmail(email);
				tenant.setPassword(password);
				tenant.setPhoneno(phoneno);
				tenant.setAddress(address);
				tenant.setOId(oid);

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
		return tenant;
	}

	
	public static List<Tenant> getTenantsByOId(int idParam)
	{
		System.out.println("--- getTenantsByOId - idParam :: " + idParam);
		
		String sql = "SELECT * FROM TENANTS WHERE OID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Tenant tenant = null;
		List<Tenant> tenantList = new ArrayList<>();
		
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
				tenant = new Tenant();

				tenant.setTId(rs.getInt("TId"));//tenant.setId(rs.getInt(1));
				tenant.setName(rs.getString("Name"));
				tenant.setAge(rs.getInt("Age"));
				tenant.setGender(rs.getString("Gender").charAt(0));
				tenant.setEmail(rs.getString("Email"));
				tenant.setPassword(rs.getString("Password"));
				tenant.setPhoneno(rs.getString("Phoneno"));
				tenant.setAddress(rs.getString("Address"));
				tenant.setOId(rs.getInt("OId"));

				//Add the tenant object into the Collection
				tenantList.add(tenant);
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
		return tenantList;

	}
	
	
	public static int createTenant(Tenant tenant)
	{
		System.out.println("--- createTenant - tenant :: " + tenant);
		
		String sql = "INSERT INTO TENANTS (Name, Age, Gender, Email, Password, Phoneno, Address, OId)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
			
			pStmt.setString(1, tenant.getName());
			pStmt.setInt(2, tenant.getAge());
			pStmt.setString(3, ""+tenant.getGender());
			pStmt.setString(4, tenant.getEmail());
			pStmt.setString(5, tenant.getPassword());
			pStmt.setString(6, tenant.getPhoneno());
			pStmt.setString(7, tenant.getAddress());
			pStmt.setInt(8, tenant.getOId());
			
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
	
	
	public static int registerTenant(Tenant tenant)
	{
		System.out.println("--- registerTenant - tenant :: " + tenant);
		
		String sql = "INSERT INTO TENANTS (Name, Age, Gender, Email, Password, Phoneno, Address, OId)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
			
			pStmt.setString(1, tenant.getName());
			pStmt.setInt(2, tenant.getAge());
			pStmt.setString(3, ""+tenant.getGender());
			pStmt.setString(4, tenant.getEmail());
			pStmt.setString(5, tenant.getPassword());
			pStmt.setString(6, tenant.getPhoneno());
			pStmt.setString(7, tenant.getAddress());
			pStmt.setInt(8, tenant.getOId());
			
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
		
		return recordsInserted;
	}

	
	public static void updateTenant(Tenant tenant) throws Exception
	{
		System.out.println("--- updateTenant - tenant :: " + tenant);
		
		String sql = "UPDATE TENANTS SET "
				+ "Name=?, Age=?, Gender=?, Email=?, Phoneno=?, Address=?, OID=? "
				+ " WHERE TID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1, tenant.getName());
		pStmt.setInt(2, tenant.getAge());
		pStmt.setString(3, ""+tenant.getGender());
		pStmt.setString(4, tenant.getEmail());
		//pStmt.setString(5, tenant.getPassword());
		pStmt.setString(5, tenant.getPhoneno());
		pStmt.setString(6, tenant.getAddress());
		pStmt.setInt(7, tenant.getOId());
		
		//Add a condition to the Where clause
		pStmt.setInt(8, tenant.getTId());
		
		recordsUpdated = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
	public static int deleteTenantByTId(int idParam)
	{
		System.out.println("--- deleteTenantByTId - idParam :: " + idParam);
		
		String sql = "DELETE FROM TENANTS WHERE TID=?";

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
			System.out.println("There are no recors deleted");
		}else {
			System.out.println("Record deleted from the table successfully!");
		}

		return rowsDeleted;
	}

		
	}
