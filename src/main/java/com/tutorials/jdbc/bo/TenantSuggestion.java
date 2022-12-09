package com.tutorials.jdbc.bo;

public class TenantSuggestion
{
	private int tsid;

	private String email;

	private String tenant_name;

	private String suggestions;


	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

	public int getTSId() {
		return this.tsid;
	}

	public void setTSId(int tsid) {
		this.tsid=tsid;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email=email;
	}


	public String getTenantName() {
		return this.tenant_name;
	}

	public void setTenantName(String tenant_name) {
		this.tenant_name = tenant_name;
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
	public TenantSuggestion() {
		System.out.println("TenantSuggestion() - instantiated..");
	}

	public TenantSuggestion(int tsid, String email, String tenant_name, String suggestions) {
		System.out.println("Tenant Suggestion(tsid, email, tenant_name, suggestions) - instantiated..");
		this.tsid = tsid;
		this.email = email;
		this.tenant_name = tenant_name;
		this.suggestions = suggestions;
	}

	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Tenant Suggestion] hashCode=" + this.hashCode()
			+ ", TSId = " + tsid
			+ ", Email = " + email
			+ ", Tenant Name = " + tenant_name 
			+ ", Suggestions = " + suggestions; 
			
	}
	
}
