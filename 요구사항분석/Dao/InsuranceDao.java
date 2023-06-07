package Dao;

import Insurance.Insurance;
import Insurance.InsuranceList;

public interface InsuranceDao {

	public int create(Insurance insurance);
	public InsuranceList retrieve();
	public InsuranceList retrieveNoConfirm();
	public InsuranceList retrieveConfirm();
	public InsuranceList retrieveNotConfirmed();
	public void updateName(String name);


}
