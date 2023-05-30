package Dao;

import Contract.Contract;
import Contract.ContractList;

public interface ContractDao {
	public int create(Contract contract);
	public ContractList retrieveContractByProcess(String processNum);
	public ContractList retrieve();
	public void update(String column , String updateQuery , Contract contract);
}

