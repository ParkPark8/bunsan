package Customer;

public class Bank {
	private String customerID  , accountNum , bankCompany , moneyAmount ,name ,passWord;

	public String getPassword() {
		return this.passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getBankCompany() {
		return bankCompany;
	}

	public void setBankCompany(String bankCompany) {
		this.bankCompany = bankCompany;
	}

	public String getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(String moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
}
