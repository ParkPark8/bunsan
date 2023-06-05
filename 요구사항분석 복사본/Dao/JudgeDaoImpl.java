package Dao;


import java.sql.ResultSet;

import Accident.AccidentList;
import Judge.Judge;
import Judge.JudgeListImpl;
import Judge.judgeList;

public class JudgeDaoImpl extends Dao implements JudgeDao{
	public  JudgeDaoImpl() {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public judgeList retrieve() {
		String query = "select * from Judge";
		judgeList judgeList = new JudgeListImpl();
		try {
		ResultSet resultset =this.retrieve(query);
		while(resultset.next()) {
			Judge judge = new Judge();
			judge.setAccidentID(resultset.getString("accidentID"));
			judge.setCoverage(resultset.getString("coverage"));
			judge.setCustomerID(resultset.getString("customerID"));
			judge.setReason(resultset.getString("reason"));
			judge.setResponsibility(resultset.getString("responsibility"));
			judgeList.add(judge);
		}
		return judgeList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String column, String updateQuery, Judge judge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int create(Judge judge) {
		String query = "insert into Judge ( accidentID , coverage , customerID , reason , responsibility) values ("+
	" '"+ judge.getAccidentID()+ "', "+
	" '"+ judge.getCoverage()+ "', "+
	" '"+ judge.getCustomerID()+ "', "+
	" '"+ judge.getReason()+ "', "+
	" '"+ judge.getResponsibility()+ "') ";
		try {
			this.execute(query);
			int judgeID =0 ;
			query = "select LAST_INSERT_ID() as ID";
			ResultSet resultSet = this.retrieve(query);
			if(resultSet.next()) {
				judgeID = resultSet.getInt("ID");
			}
			return judgeID;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
