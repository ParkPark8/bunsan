package Accident;

import java.util.ArrayList;

public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> AccidentList;

	public AccidentListImpl(){
		this.AccidentList = new ArrayList<Accident>();
	}

	public ArrayList<Accident> getContractList(){return this.AccidentList;}
	public void setAccidentList(ArrayList<Accident> accidents) {this.AccidentList = accidents;}
	

	public boolean add(Accident accident) {
		if (this.AccidentList.add(accident)) return true;
		return false;
	}

	@Override
	public Accident search(int accidentNumber) {
		for(Accident accidents : this.AccidentList) {
			if(accidents.getAccidentID() == accidentNumber ) return accidents;
		}
		return null;
	}
	@Override
	public boolean delete() {
		return false;
	}

	public void update(){

	}

	@Override
	public ArrayList<Accident> getAccidentList() {
        return this.AccidentList;
	}



}