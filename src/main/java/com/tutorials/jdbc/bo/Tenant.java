package com.tutorials.jdbc.bo;

public class Tenant
{
	private int tid;

	private String name;

	private int age;

	private char gender;

	private String email;

	private String password;
	
	private String phoneno;
	
	private String address;
	
	private int oid;

	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

	public int getTId() {
		return this.tid;
	}

	public void setTId(int tid) {
		this.tid=tid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneno() {
		return this.phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getOId() {
		return this.oid;
	}

	public void setOId(int oid) {
		this.oid=oid;
	}
	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */
	public Tenant() {
		System.out.println("Tenant() - instantiated..");
	}

	public Tenant(int tid, String name, int age, char gender, String email, String password, String phoneno, String address, int oid) {
		System.out.println("Tenant(tid, name, age, gender, email, password, phoneno, address, oid) - instantiated..");
		this.tid = tid;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.phoneno = phoneno;
		this.address = address;
		this.oid = oid;
	}

	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Tenant] hashCode=" + this.hashCode()
			+ ", TId = " + tid  
			+ ", Name = " + name 
			+ ", Age = " + age 
			+ ", gender = " + gender  
			+ ", email = " + email
			+ ", password = " + password
			+ ", phoneno = " + phoneno
			+ ", address = " + address
			+ ", oid = " + oid;
	}

}
