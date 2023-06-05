package Dao;


import Accident.Accident;
import Accident.AccidentList;
import Customer.Bank;

public interface AccidentDao {


	public AccidentList retrieveAccidentByProcess(String accidentNumber);
	public void update(String column , String updateQuery , Accident accident);
	public AccidentList retrieve();
	void create(Accident accident);
}


