package Dao;

import Contract.Contract;
import Contract.ContractList;
import Customer.Customer;

public interface ContractDao {
	public int create(Contract contract);
	public ContractList retrieveContractByProcess(String processNum);
	public ContractList retrieve();
	public ContractList retrieveByCustomerID(Customer customer);
	public void update(String column , String updateQuery , Contract contract);
}

