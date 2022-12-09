package com.tutorials.jdbc.bo;

public class Owner
{
	private int oid;

	private String name;

	private int age;

	private char gender;

	private String email;

	private String password;
	
	private String phoneno;
	
	private String address;

	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

	public int getOId() {
		return this.oid;
	}

	public void setOId(int oid) {
		this.oid=oid;
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
	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */
	public Owner() {
		System.out.println("Owner() - instantiated..");
	}

	public Owner(int oid, String name, int age, char gender, String email, String password, String phoneno, String address) {
		System.out.println("Owner(oid, name, age, gender, email, password, phoneno, address) - instantiated..");
		this.oid = oid;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.phoneno = phoneno;
		this.address = address;
	}

	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Owner] hashCode=" + this.hashCode()
			+ ", OId = " + oid  
			+ ", Name = " + name 
			+ ", Age = " + age 
			+ ", gender = " + gender  
			+ ", email = " + email
			+ ", password = " + password
			+ ", phoneno = " + phoneno
			+ ", address = " + address;
	}

}
