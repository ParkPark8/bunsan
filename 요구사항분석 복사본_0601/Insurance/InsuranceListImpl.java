package Insurance;

import java.util.ArrayList;


/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class InsuranceListImpl implements InsuranceList {
	//attribute
	private ArrayList<Insurance> insuranceList;
	public Insurance m_insurance;

	public InsuranceListImpl(){
		this.insuranceList = new ArrayList<Insurance>();
	}
	//getter & setter
	public ArrayList<Insurance> getInsuranceList(){return insuranceList;}
	public void setInsuranceList(ArrayList<Insurance> insurances){this.insuranceList = insurances;}
	
	public boolean add(Insurance insurance){
		if(this.insuranceList.add(insurance)) return true;
		return false;
	}

	public boolean delete(int insuranceId){
		for(Insurance insurance : this.insuranceList) {
			if(insurance.getInsuranceID()==(insuranceId)) {
				if(this.insuranceList.remove(insurance)) return true;
			}
		}
		return false;
	}

	public Insurance search(int InsuranceID){
		for(Insurance insurance : this.insuranceList) {
			if(insurance.getInsuranceID()==(InsuranceID)) return insurance;
		}
		return null;
	}

	public boolean update(){
		return false;
	}
	public void showUnpassed() {
		for(Insurance insurance : this.insuranceList) {
			if(!insurance.isPassed())
				System.out.println( insurance.getInsuranceID()+" " + insurance.getInsuranceName());
		}
	}
	public void show() {
		for(Insurance insurance : this.insuranceList) {
				System.out.println( insurance.getInsuranceID()+" " + insurance.getInsuranceName());
		}
	
		
	}
}