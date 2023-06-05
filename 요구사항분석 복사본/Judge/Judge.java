package Judge;

public class Judge {
	//judgeID , acccidentID , customerID , responsibility , reason 
	//coverage
	private int judgeID;
	private String accidentID , customerID , responsibility , reason , coverage ;
	public int getJudgeID() {
		return judgeID;
	}
	public void setJudgeID(int judgeID) {
		this.judgeID = judgeID;
	}
	public String getAccidentID() {
		return accidentID;
	}
	public void setAccidentID(String accidentID) {
		this.accidentID = accidentID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	
}
