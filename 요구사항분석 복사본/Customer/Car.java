package Customer;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:42
 */
public class Car {

	private String carType , madeYear,carNum,customerID;
	
	public Car(){

	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getMadeYear() {
		return this.madeYear;
	}

	public void setMadeYear(String madeYear) {
		this.madeYear = madeYear;
	}

	public String getCarNum() {
		return this.carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID ;
	}
	public String getCustomerID() {
		return this.customerID;
	}

}