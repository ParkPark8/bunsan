package Dao;

import java.sql.ResultSet;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;

public class ContractDaoImpl extends Dao implements ContractDao{
	public ContractDaoImpl() {
		try	{
			this.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int create(Contract contract) {
		String query = "insert into Contract (customerID , employeeID , insuranceID , startDate , endDate , payDate , registFee , monthFee , processNum )  values ( "+
		" '" + contract.getCustomerID()+ "', "+
		" '" + contract.getEmployeeID()+ "', "+
		" '" + contract.getInsuranceID()+ "', "+
		" '" + contract.getStartDate()+ "', "+
		" '" + contract.getEndDate()+ "', "+
		" '" + contract.getPayDate()+ "', "+
		" '" + contract.getRegistFee()+ "', "+
		" '" + contract.getMonthFee()+ "', "+
		" '" + contract.getProcessNum()+ "') ";
		try {
		this.execute(query);
		int contractID =0;
		query = "select LAST_INSERT_ID() as ID";
		ResultSet resultSet = this.retrieve(query);
		
		while(resultSet.next()) {
			contractID = resultSet.getInt("ID");
		}
		return contractID;}
		catch (Exception e) {
			e.printStackTrace();
		}return 0;
	}

	@Override
	public ContractList retrieveContractByProcess(String processNum) { //1 / 2 / 3 / 4 단계별로 나누어서 . 
		String query = "select * from Contract WHERE processNum = '" +  processNum + "'" ;
		ContractList contractList = new ContractListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Contract contract = new Contract();
				contract.setContractID(resultSet.getInt("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setEmployeeID(resultSet.getString("employeeID"));
				contract.setInsuranceID(resultSet.getString("insuranceID"));
				contract.setStartDate(resultSet.getString("startDate"));
				contract.setEndDate(resultSet.getString("endDate"));
				contract.setPayDate(resultSet.getString("payDate"));
				contract.setRegistFee(resultSet.getString("registFee"));
				contract.setMonthFee(resultSet.getString("monthFee"));
				contract.setProcessNum(processNum);
				contractList.add(contract);
			}
			return contractList;
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

	@Override
	public void update(String column, String updateQuery, Contract contract) {
		String contractID = contract.getContractID()+"";
		String query = "UPDATE Contract SET "+ column+" = "+updateQuery+" WHERE contractID = '"+contractID+"'"; 
		try {
			this.update(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public ContractList retrieve() { //1 / 2 / 3 / 4 단계별로 나누어서 . 
		String query = "select * from Contract ";
		ContractList contractList = new ContractListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Contract contract = new Contract();
				contract.setContractID(resultSet.getInt("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setEmployeeID(resultSet.getString("employeeID"));
				contract.setInsuranceID(resultSet.getString("insuranceID"));
				contract.setStartDate(resultSet.getString("startDate"));
				contract.setEndDate(resultSet.getString("endDate"));
				contract.setPayDate(resultSet.getString("payDate"));
				contract.setRegistFee(resultSet.getString("registFee"));
				contract.setMonthFee(resultSet.getString("monthFee"));
				contract.setProcessNum(resultSet.getString("processNum"));
				contractList.add(contract);
			}
			return contractList;
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

}
