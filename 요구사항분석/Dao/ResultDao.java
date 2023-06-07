package Dao;

import Insurance.Result;

public interface ResultDao {
	public void create(Result result);
	public Result retrieveByInsuranceID(String insuranceID);
	public void update(String column , String updateQuery , Result result);
}
