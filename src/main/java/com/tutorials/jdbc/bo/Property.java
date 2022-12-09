package com.tutorials.jdbc.bo;

public class Property 
{
	private int pid;
	
	private String property_name;
	
	private String door_no;
	
	private String street_name;
	
	private String city;
	
	private String pincode;
	
	private String state;
	
	private String country;
	
	private String area;
	
	private String facing;
	
	private String purchase_value;
	
	private String flat_no;
	
	private String address;
	
	private String property_tax;
	
	private String water_tax;
	
	private String electricity_charges;
	
	private int oid;
	
	private String owner_name;
	
	private int tid;
	
	private String tenant_name;
	
	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

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
	
	public String getDoorNo() {
		return this.door_no;
	}
	
	public void setDoorNo(String door_no) {
		this.door_no = door_no;
	}
	
	public String getStreetName() {
		return this.street_name;
	}

	public void setStreetName(String street_name) {
		this.street_name = street_name;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getFacing() {
		return this.facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}
	
	public String getPurchaseValue() {
		return this.purchase_value;
	}

	public void setPurchaseValue(String purchase_value) {
		this.purchase_value = purchase_value;
	}
	
	public String getFlatNo() {
		return this.flat_no;
	}

	public void setFlatNo(String flat_no) {
		this.flat_no = flat_no;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPropertyTax() {
		return this.property_tax;
	}

	public void setPropertyTax(String property_tax) {
		this.property_tax = property_tax;
	}
	
	public String getWaterTax() {
		return this.water_tax;
	}

	public void setWaterTax(String water_tax) {
		this.water_tax = water_tax;
	}
	
	public String getElectricityCharges() {
		return this.electricity_charges;
	}

	public void setElectricityCharges(String electricity_charges) {
		this.electricity_charges = electricity_charges;
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
	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */
	public Property() {
		System.out.println("Property() - instantiated..");
	}
	
	public Property(int pid, String property_name, String door_no, String street_name, String city, String pincode, String state, String country, String area, String facing, String purchase_value, String flat_no, String address, String property_tax, String water_tax, String electricity_charges, int oid, String owner_name, int tid, String tenant_name) {
		System.out.println("Tenant(tid, name, age, gender, email, password, phoneno, address, oid) - instantiated..");
		this.pid = pid;
		this.property_name = property_name;
		this.door_no = door_no;
		this.street_name = street_name;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
		this.area = area;
		this.facing = facing;
		this.purchase_value = purchase_value;
		this.flat_no = flat_no;
		this.address = address;
		this.property_tax = property_tax;
		this.water_tax = water_tax;
		this.electricity_charges = electricity_charges;
		this.oid = oid;
		this.owner_name = owner_name;
		this.tid = tid;
		this.tenant_name = tenant_name;
	}
	
	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Property] hashCode=" + this.hashCode()
			+ ", PId = " + pid  
			+ ", property_name = " + property_name 
			+ ", door_no = " + door_no 
			+ ", street_name = " + street_name  
			+ ", city = " + city
			+ ", pincode = " + pincode
			+ ", state = " + state
			+ ", country = " + country
			+ ", area = " + area
			+ ", facing = " + facing
			+ ", purchase_value = " + purchase_value
			+ ", flat_no = " + flat_no
			+ ", address = " + address
			+ ", property_tax = " + property_tax
			+ ", water_tax = " + water_tax
			+ ", electricity_charges = " + electricity_charges
			+ ", oid = " + oid
			+ ", owner_name = " + owner_name
			+ ", tid = " + tid
			+ ", tenant_name = " + tenant_name;
	}


}
