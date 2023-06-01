package Dao;

import Customer.Bank;
import Customer.Customer;

public interface BankDao {
	public void create(Bank bank);
	public Bank retrieve(Customer customer);
}
