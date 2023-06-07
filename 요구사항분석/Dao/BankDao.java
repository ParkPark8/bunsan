package Dao;

import Contract.Contract;
import Customer.Bank;
import Customer.Customer;

public interface BankDao {
	public void create(Bank bank);
	public Bank retrieve(Customer customer);
	void update(String column, String updateQuery, Customer customer );
}
