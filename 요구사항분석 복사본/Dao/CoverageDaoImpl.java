package Dao;

import java.sql.ResultSet;

import Insurance.Coverage;

public class CoverageDaoImpl extends Dao implements CoverageDao {
	public CoverageDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int create(Coverage coverage) {
		String query = "insert into Coverage ( insuranceID , coverageCondition , coverageCost ) values ("+
				coverage.getInsuranceID() + ", "+
				" '" + coverage.getCoverageCondition() + "', "+
				coverage.getCoverageCost()+")";
		int coverageID = 0 ; 
		try {
			this.execute(query);
			
			query = "select LAST_INSERT_ID() as ID";
			ResultSet resultSet = this.retrieve(query);
			
			while(resultSet.next()) {
				coverageID = resultSet.getInt("ID");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return coverageID;
	}
}
