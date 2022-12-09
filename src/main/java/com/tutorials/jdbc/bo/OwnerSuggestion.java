package com.tutorials.jdbc.bo;

public class OwnerSuggestion {
	
	private int osid;

	private String email;

	private String owner_name;

	private String suggestions;


	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

	public int getOSId() {
		return this.osid;
	}

	public void setOSId(int osid) {
		this.osid=osid;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email=email;
	}


	public String getOwnerName() {
		return this.owner_name;
	}

	public void setOwnerName(String owner_name) {
		this.owner_name = owner_name;
	}

		
	public String getSuggestions() {
		return this.suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */
	public OwnerSuggestion() {
		System.out.println("OwnerSuggestion() - instantiated..");
	}

	public OwnerSuggestion(int osid, String email, String owner_name, String suggestions) {
		System.out.println("Owner Suggestion(osid, email, tenant_name, suggestions) - instantiated..");
		this.osid = osid;
		this.email = email;
		this.owner_name = owner_name;
		this.suggestions = suggestions;
	}

	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Owner Suggestion] hashCode=" + this.hashCode()
			+ ", OSId = " + osid
			+ ", Email = " + email
			+ ", Tenant Name = " + owner_name 
			+ ", Suggestions = " + suggestions; 
			
	}


}
