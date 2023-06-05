package Contract;

import Accident.Accident;
/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:42
 */
public class Contract {
	private int contractID;
	
	private String   customerID , employeeID ,insuranceID;
	
	private String startDate,endDate ,payDate , registFee , monthFee ;
	//가입일 , 만기일 , 월 납부일 , 가입 금액 , 월 납부액 
	private String processNum; // 1 : 신청 2 :체결 3 : 인수 완 4 : 인수x
	
	//getter setter
	public String getRegistFee() {
		return registFee;
	}
	public void setRegistFee(String registFee) {
		this.registFee = registFee;
	}
	public String getProcessNum() {
		return this.processNum;
	}
	public void setProcessNum(String processNum) {
		this.processNum = processNum;
	}
	public Contract(){}
	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getMonthFee() {
		return monthFee;
	}
	public void setMonthFee(String monthFee) {
		this.monthFee = monthFee;
	}
	

}