package Insurance;

import Interview.InterviewList;

public class Confirm {
	private String confirmDate, confirmRefuse, insuranceProblem;
	private int confirmed; //0 : Refused 1 : confirmed
	private int insuranceID;
	public Confirm(){}
	
	public int getInsuranceID() { return this.insuranceID; }
	public void setInsuranceID(int insuranceID) { this.insuranceID = insuranceID; }
	public int getconfirmed() { return confirmed; }
	public void setConfirmed(int confirmed) { this.confirmed = confirmed; }
	public String getConfirmDate() {return confirmDate;}
	public void setConfirmDate(String confirmDate) {this.confirmDate = confirmDate;}
	public String getConfirmRefuse() {return confirmRefuse;}
	public void setConfirmRefuse(String confirmRefuse) {this.confirmRefuse = confirmRefuse;}
	public String getInsuranceProblem() {return insuranceProblem;}
	public void setInsuranceProblem(String insuranceProblem) {this.insuranceProblem = insuranceProblem;}
}
