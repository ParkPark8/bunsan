package Dao;

import Insurance.Ratio;

public interface RatioDao {
	public void create(Ratio ratio);
	public Ratio retrieveByInsuranceID(int insuranceID);
}
