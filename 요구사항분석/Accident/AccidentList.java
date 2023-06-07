package Accident;

import java.util.ArrayList;

public interface AccidentList {
	public boolean add(Accident accident);
	public boolean delete();
	public void update();
	public Accident search(int accidentNumber);
	public ArrayList<Accident> getAccidentList();


}