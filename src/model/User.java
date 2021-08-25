package model;

public class User {
	private String name;
	private long mobnum;
	private int pincode;
	private int userid;
	private Assessment assessment;
	private int riskPercent;
	private String userType;
	private String covidStatus;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCovidStatus() {
		return covidStatus;
	}
	public void setCovidStatus(String covidStatus) {
		this.covidStatus = covidStatus;
	}
	public int getRiskPercent() {
		return riskPercent;
	}
	public void setRiskPercent(int riskPercent) {
		this.riskPercent = riskPercent;
	}
	public Assessment getAssessment() {
		return assessment;
	}
	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobnum() {
		return mobnum;
	}
	public void setMobnum(long mobnum) {
		this.mobnum = mobnum;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", mobnum=" + mobnum + ", pincode=" + pincode + ", userid=" + userid
				+ ", assessment=" + assessment + ", riskPercent=" + riskPercent + ", userType=" + userType
				+ ", covidStatus=" + covidStatus + "]";
	}

}
