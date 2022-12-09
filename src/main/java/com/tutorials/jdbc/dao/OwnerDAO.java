package com.tutorials.jdbc.dao;
import com.tutorials.jdbc.bo.Owner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO
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

		List<Owner> ownerList = ownerListAll();
		System.out.println("ownerList size :  " + ownerList.size());

		if(ownerList.size()>0) {
			for(Owner owner : ownerList) {
				System.out.println(owner);
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
	
	public static List<Owner> ownerListAll()
	{
		System.out.println("--- ownerListAll invoked --- ");
		
		String sql = "SELECT * FROM OWNERS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Owner> ownerList = new ArrayList<>();
		Owner owner = null;
		
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
				//create a new instance of Person whenever there is a data from the ResultSet
				owner = new Owner();
				
				owner.setOId(rs.getInt("OId"));//owner.setOId(rs.getInt(1));
				owner.setName(rs.getString("Name"));
				owner.setAge(rs.getInt("Age"));
				owner.setGender(rs.getString("Gender").charAt(0));
				owner.setEmail(rs.getString("Email"));
				owner.setPassword(rs.getString("Password"));
				owner.setPhoneno(rs.getString("Phoneno"));
				owner.setAddress(rs.getString("Address"));
			

				//Add the owner object into the Collection
				ownerList.add(owner);
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


		return ownerList;
	}	
	
	public static Owner getOwnerById(int idParam)
	{
		System.out.println("--- getOwnerById - idParam :: " + idParam);
		
		String sql = "SELECT * FROM OWNERS WHERE OID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Owner owner = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
			int oid, age;
			String name, email, password, phoneno, address;
			char gender;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				owner = new Owner();

				oid = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				gender = rs.getString(4).charAt(0);
				email = rs.getString(5);
				password = rs.getString(6);
				phoneno = rs.getString(7);
				address = rs.getString(8);


				owner.setOId(oid);
				owner.setName(name);
				owner.setAge(age);
				owner.setGender(gender);
				owner.setEmail(email);
				owner.setPassword(password);
				owner.setPhoneno(phoneno);
				owner.setAddress(address);

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

		return owner;
	}
	
	public static Owner getOwnerByEmail(String emailParam)
	{
		System.out.println("--- getOwnerByEmail - idParam :: " + emailParam);
		
		String sql = "SELECT * FROM OWNERS WHERE EMAIL=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Owner owner = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, emailParam);

			rs = stmt.executeQuery();
		
			int oid, age;
			String name, email, password, phoneno, address;
			char gender;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				owner = new Owner();

				oid = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				gender = rs.getString(4).charAt(0);
				email = rs.getString(5);
				password = rs.getString(6);
				phoneno = rs.getString(7);
				address = rs.getString(8);


				owner.setOId(oid);
				owner.setName(name);
				owner.setAge(age);
				owner.setGender(gender);
				owner.setEmail(email);
				owner.setPassword(password);
				owner.setPhoneno(phoneno);
				owner.setAddress(address);

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

		return owner;
	}


	
	public static int createOwner(Owner owner)
	{
		System.out.println("--- createOwner - owner :: " + owner);
		
		String sql = "INSERT INTO OWNERS (Name, Age, Gender, Email, Password, Phoneno, Address)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?)";

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
			
			pStmt.setString(1, owner.getName());
			pStmt.setInt(2, owner.getAge());
			pStmt.setString(3, ""+owner.getGender());
			pStmt.setString(4, owner.getEmail());
			pStmt.setString(5, owner.getPassword());
			pStmt.setString(6, owner.getPhoneno());
			pStmt.setString(7, owner.getAddress());
			
			recordsInserted = pStmt.executeUpdate();
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
			System.err.println("SQL Exception occurred while inserting the data to the Database Table");
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
	
	public static int registerOwner(Owner owner)
	{
		System.out.println("--- createOwner - owner :: " + owner);
		
		String sql = "INSERT INTO OWNERS (Name, Age, Gender, Email, Password, Phoneno, Address)" + 
					" VALUES (?, ?, ?, ?, ?, ?, ?)";

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
			
			pStmt.setString(1, owner.getName());
			pStmt.setInt(2, owner.getAge());
			pStmt.setString(3, ""+owner.getGender());
			pStmt.setString(4, owner.getEmail());
			pStmt.setString(5, owner.getPassword());
			pStmt.setString(6, owner.getPhoneno());
			pStmt.setString(7, owner.getAddress());
			
			recordsInserted = pStmt.executeUpdate();
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
			System.err.println("SQL Exception occurred while inserting the data to the Database Table");
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

	
	public static void updateOwner(Owner owner) throws Exception
	{
		System.out.println("--- updateOwner - owner :: " + owner);
		
		String sql = "UPDATE OWNERS SET "
				+ "Name=?, Age=?, Gender=?, Email=?, Phoneno=?, Address=? "
				+ " WHERE OID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1, owner.getName());
		pStmt.setInt(2, owner.getAge());
		pStmt.setString(3, ""+owner.getGender());
		pStmt.setString(4, owner.getEmail());
		//pStmt.setString(5, owner.getPassword());
		pStmt.setString(5, owner.getPhoneno());
		pStmt.setString(6, owner.getAddress());
		
		//Add a condition to the Where clause
		pStmt.setInt(7, owner.getOId());
		
		recordsUpdated = pStmt.executeUpdate();
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
	public static int deleteOwnerByOId(int idParam)
	{
		System.out.println("--- deleteOwnerByOId - idParam :: " + idParam);
		
		String sql = "DELETE FROM OWNERS WHERE OID=?";

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
