package Dao;

import Insurance.Confirm;

public interface ConfirmDao {
	public void create(Confirm confirm);
	public Confirm retrieveByInsuranceID(int insuranceID);
}
