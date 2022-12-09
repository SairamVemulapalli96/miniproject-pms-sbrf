package com.tutorials.jdbc.bo;

public class Agreement {
	
	private int aid;
	
	private int oid;
	
	private String owner_name;
	
	private int pid;
	
	private String property_name;
	
	private int tid;
	
	private String tenant_name;
	
	private String tenancy_start_date;
	
	private String tenancy_end_date;
	
	private int rent;
	
	private int maintenance;
	
	private String terms;
	
	public int getAId() {
		return this.aid;
	}

	public void setAId(int aid) {
		this.aid=aid;
	}
	
	public int getOId() {
		return this.oid;
	}
	
	public void setOId(int oid) {
		this.oid=oid;
	}
	
	public String getOwnerName() {
		return this.owner_name;
	}

	public void setOwnerName(String owner_name) {
		this.owner_name = owner_name;
	}
	
	public int getPId() {
		return this.pid;
	}

	public void setPId(int pid) {
		this.pid=pid;
	}
	
	public String getPropertyName() {
		return this.property_name;
	}

	public void setPropertyName(String property_name) {
		this.property_name = property_name;
	}

	public int getTId() {
		return this.tid;
	}

	public void setTId(int tid) {
		this.tid=tid;
	}

	public String getTenantName() {
		return this.tenant_name;
	}

	public void setTenantName(String tenant_name) {
		this.tenant_name = tenant_name;
	}
	
	
	public String getTenancyStartDate() {
		return this.tenancy_start_date;
	}

	public void setTenancyStartDate(String tenancy_start_date) {
		this.tenancy_start_date = tenancy_start_date;
	}
	
	public String getTenancyEndDate() {
		return this.tenancy_end_date;
	}

	public void setTenancyEndDate(String tenancy_end_date) {
		this.tenancy_end_date = tenancy_end_date;
	}
	
	public int getRent() {
		return this.rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	
	public int getMaintenance() {
		return this.maintenance;
	}

	public void setMaintenance(int maintenance) {
		this.maintenance = maintenance;
	}
	
	public String getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */
	public Agreement() {
		System.out.println("Agreement() - instantiated..");
	}
	
	public Agreement(int aid, int oid, String owner_name, int pid, String property_name, int tid, String tenant_name, String tenancy_start_date, String tenancy_end_date, int rent, int maintenance, String terms)
	{
		this.aid = aid;
		this.oid = oid;
		this.owner_name = owner_name;
		this.pid = pid;
		this.property_name = property_name;
		this.tid = tid;
		this.tenant_name = tenant_name;
		this.tenancy_start_date = tenancy_start_date;
		this.tenancy_end_date = tenancy_end_date;
		this.rent = rent;
		this.maintenance = maintenance;
		this.terms = terms;
	
	}
	
	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Property] hashCode=" + this.hashCode()
			+ ", AId = " + aid
			+ ", oid = " + oid
			+ ", owner_name = " + owner_name
			+ ", pid = " + pid
			+ ", property_name = " + property_name
			+ ", tid = " + tid
			+ ", tenant_name = " + tenant_name
			+ ", tenancy_start_date = " + tenancy_start_date
			+ ", tenancy_end_date = " + tenancy_end_date
			+ ", rent = " + rent
			+ ", maintenance = " + maintenance
			+ ", terms = " + terms;
	}

}
