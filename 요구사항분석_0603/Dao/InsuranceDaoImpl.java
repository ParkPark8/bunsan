package Dao;

import java.sql.ResultSet;

import Insurance.Confirm;
import Insurance.Coverage;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;
import Interview.InterviewList;

public class InsuranceDaoImpl extends Dao implements InsuranceDao {
	public InsuranceDaoImpl() {
		try {
			super.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int create(Insurance insurance) {
		String query = "select * from insurance where insuranceName = '" + insurance.getInsuranceName() +"'";
		int insuranceID = 0;
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()	) {
				insuranceID = resultSet.getInt("insuranceID");
			}
			if( insuranceID != 0) return 0; // 존재한다 
			
			query = "insert into Insurance ( insuranceName , productType , insuranceFee ) values ("+
			" '" + insurance.getInsuranceName() + "', "+
			" '" + insurance.getProductType() + "', "+
			" " + insurance.getInsuranceFee() +" )";
			
			this.execute(query);
			
			query = "select LAST_INSERT_ID() as ID";
			resultSet = this.retrieve(query);
			
			while(resultSet.next()) {
				insuranceID = resultSet.getInt("ID");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return insuranceID;
	}

	@Override
	public InsuranceList retrieve() {
		String query = "select * from Insurance";
		InsuranceList insuranceList = new InsuranceListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setInsuranceFee(resultSet.getInt("insuranceFee"));
				insurance.setProductType(resultSet.getString("productType"));
				
				insuranceList.add(insurance);
			}
			for(  int i = 0; i<insuranceList.getInsuranceList().size(); i++) {
				Insurance insurance = insuranceList.getInsuranceList().get(i);
				query = "select * from coverage where insuranceID =  " + insurance.getInsuranceID();
				
				resultSet = this.retrieve(query);
				while(resultSet.next()) {
					Coverage coverage = new Coverage();
					coverage.setCoverageID(resultSet.getInt("coverageID"));
					coverage.setInsuranceID(resultSet.getInt("insuranceID"));
					coverage.setCoverageCost(resultSet.getInt("coverageCost"));
					String condition = resultSet.getString("coverageCondition");
					
					switch (condition) {
					case "high": insurance.setHCoverage(coverage);
						break;
					case "middle": insurance.setMCoverage(coverage);;
						break;
					case "low": insurance.setLCoverage(coverage);
						break;
					default:
						break;
					}
				}
	            query = "select * from Confirm where insuranceID = " + insurance.getInsuranceID();
	            
	            resultSet = this.retrieve(query);
	            
	            while(resultSet.next()) {
	            	Confirm confirm = new Confirm();
	            	confirm.setConfirmDate(resultSet.getString("confirmDate"));
	            	int confirmed = resultSet.getInt("confirmed");
	            	confirm.setConfirmed(confirmed);
	            	if( confirmed == 0 ) {
	            		confirm.setInsuranceProblem(resultSet.getString("insuranceProblem"));
	            		confirm.setConfirmRefuse(resultSet.getString("confirmRefuse"));
	            	}
	            	insurance.setConfirm(confirm);
	            }
			}
			return insuranceList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InsuranceList retrieveNoConfirm() {
		String query = "select * from insurance left outer join Confirm using(insuranceID) where Confirm.insuranceID IS NULL";
		InsuranceList insuranceList = new InsuranceListImpl();
		try {
			ResultSet resultSet = this.retrieve(query);
			
			while(resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setProductType(resultSet.getString("productType"));
				insurance.setInsuranceFee(resultSet.getInt("insuranceFee"));
				
				insuranceList.add(insurance);
			}
			return insuranceList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public InsuranceList retrieveConfirm() {
		String query = "select * from Insurance left outer join Confirm using(insuranceID) where Confirm.confirmed = 1";
		InsuranceList insuranceList = new InsuranceListImpl();
		try {
			ResultSet resultSet = this.retrieve(query);
			
			while(resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setProductType(resultSet.getString("productType"));
				insurance.setInsuranceFee(resultSet.getInt("insuranceFee"));
				insuranceList.add(insurance);
			}
			return insuranceList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InsuranceList retrieveNotConfirmed() {
		String query = "select * from Insurance left outer join Confirm using(insuranceID) where Confirm.confirmed = 0";
		InsuranceList insuranceList = new InsuranceListImpl();
		try {
			ResultSet resultSet = this.retrieve(query);
			
			while(resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setProductType(resultSet.getString("productType"));
				insurance.setInsuranceFee(resultSet.getInt("insuranceFee"));
				
				insuranceList.add(insurance);
			}
			return insuranceList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public void updateName(String name) {
		// TODO Auto-generated method stub
		
	}


}
