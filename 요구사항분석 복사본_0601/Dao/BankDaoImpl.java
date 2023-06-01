package Dao;

import java.sql.ResultSet;

import javax.print.attribute.standard.JobName;

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
		String query = "insert into Bank (customerID , accountNum , bankCompany , moneyAmount ,name) "
				+ "values ("+
				" '" +bank.getCustomerID() + "', "+
				" '" + bank.getAccountNum()+ "', "+
				" '" + bank.getBankCompany()+ "', "+
				" ' " + bank.getMoneyAmount()+ "', "+
				" '" + bank.getName()+"') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bank retrieve(Customer customer) {
		String query = "select * from Bank WHERE customerID = " + customer.getCustomerID() +"";
		Bank bank = new Bank();
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				bank.setAccountNum(resultSet.getString("accountNum"));
				bank.setBankCompany(resultSet.getString("bankCompany"));
				bank.setCustomerID(customer.getCustomerID()+"");
				bank.setMoneyAmount(resultSet.getString("moneyAmount"));
				bank.setName(resultSet.getString("name"));
			}return bank;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
