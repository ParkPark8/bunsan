package Dao;

import java.sql.ResultSet;

import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare.Builder;

import Insurance.Result;

public class ResultDaoImpl extends Dao implements ResultDao{
	public ResultDaoImpl() {
		try {
			this.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void create(Result result) {
		String query = "insert into Result (insuranceID, goal , numSelled, statement , problem ) values ("+
	" '" +result.getInsuranceID() + "', "+
	" '" + result.getGoal()+ "', "+
	" '" + result.getNumSelled()+ "', "+
	" '" +result.getStatement() + "', "+
	" '" + result.getProblem()+ "') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Result retrieveByInsuranceID(String insuranceID) {
		String query = "select * from Result WHERE insuranceID = '"+insuranceID+"'";
		Result result = new Result();
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()	) {
				result.setGoal(resultSet.getString("goal"));
				result.setInsuranceID(insuranceID);
				result.setNumSelled(resultSet.getString("numSelled"));
				result.setStatement(resultSet.getString("statement"));
				result.setProblem(resultSet.getString("problem"));
			}return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String column, String updateQuery, Result result) {
		String insuranceID = result.getInsuranceID();
		String query = "UPDATE Result SET "+ column+" = "+updateQuery+" WHERE insuranceID = '"+insuranceID+"'"; 
		try {
			this.update(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
