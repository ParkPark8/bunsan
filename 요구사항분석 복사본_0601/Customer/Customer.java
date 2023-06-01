package Customer;

import java.util.ArrayList;

import Accident.Accident;
import Contract.Contract;
import Insurance.Insurance;
import Insurance.InsuranceList;

/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public class Customer {
	//attribute
	private String customerID;
	private String address;
	private String age;
	private String job;
	private String name;
	private String phoneNumber;
	private String sex;
	private String SID;
	private String isInterview; // 0 : n 1 : 진행중 2: Y
	private String isBank; // 0 : n 1 : Y
	//
	public Contract m_contract;
	public Car m_Car;

	public Customer(){}

	public String getIsBank() {
		return this.isBank;
	}

	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSID() {
		return this.SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public Contract getM_contract() {
		return this.m_contract;
	}

	public void setM_contract(Contract m_contract) {
		this.m_contract = m_contract;
	}

	public Car getM_Car() {
		return this.m_Car;
	}

	public void setM_Car(Car m_Car) {
		this.m_Car = m_Car;
	}
	public String getIsInterview() {
		return this.isInterview;
	}
	public void setIsInterview(String isinterview) {
		this.isInterview = isinterview;
	}
	
	
	
}