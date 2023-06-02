package Dao;

import java.sql.ResultSet;

import javax.print.attribute.standard.JobName;

import Contract.Contract;
import Customer.Bank;
import Customer.Customer;

public class BankDaoImpl extends Dao implements BankDao{
	public BankDaoImpl() {
		try {
			this.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(Bank bank) {
		String query = "insert into Bank (customerID , accountNum , bankCompany , moneyAmount ,name,passWord) "
				+ "values ("+
				" '" +bank.getCustomerID() + "', "+
				" '" + bank.getAccountNum()+ "', "+
				" '" + bank.getBankCompany()+ "', "+
				" '" + bank.getMoneyAmount()+ "', "+
				" '" + bank.getName()+"', "+
				" '" + bank.getPassword()+"') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bank retrieve(Customer customer) {
		String query = "select * from Bank WHERE customerID = '" + customer.getCustomerID() +"'";
		Bank bank = new Bank();
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				bank.setAccountNum(resultSet.getString("accountNum"));
				bank.setBankCompany(resultSet.getString("bankCompany"));
				bank.setCustomerID(customer.getCustomerID()+"");
				bank.setMoneyAmount(resultSet.getString("moneyAmount"));
				bank.setName(resultSet.getString("name"));
				bank.setPassWord(resultSet.getString("passWord"));
			}return bank;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public void update(String column, String updateQuery, Customer customer) {
		String customerID = customer.getCustomerID();
		String query = "UPDATE Bank SET "+column+" = "+updateQuery+" WHERE customerID = '"+customerID+"'"; 
		try {
			this.update(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
