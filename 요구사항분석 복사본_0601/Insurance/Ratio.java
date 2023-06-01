package Insurance;

public class Ratio {
	private int InsuranceID;
	//car
	private String smallRatio , freightRatio , sedanRatio , busRatio , motorCycleRatio ;
	//Job
	private String studentRatio , deliverRatio , busDriverRatio , freightDriverRatio , etcRatio ;
	//age
	private String ratio20 , ratio30 , ratio40 , ratio50 , ratio60 ;
	//sex
	private String maleRatio , femaleRatio;
	//madeYear
	private String after2018 , before2018;
	public Ratio() {
		//car
		this.smallRatio =  "0.8"; 
		this.freightRatio = "1.2"; 
		this.sedanRatio = "0.9";
		this.busRatio = "1.1";
		this.motorCycleRatio =  "1.3";
		//job
		this.studentRatio =  "0.8";
		this.deliverRatio = "1.3";
		this.busDriverRatio = "1.2";
		this.freightDriverRatio = "1.4";
		this.etcRatio = "0.9";
		//age
		this.ratio20 =  "1.2";
		this.ratio30 =  "1.1";
		this.ratio40 =  "0.9";
		this.ratio50 =  "1.2";
		this.ratio60 = "1.4";
		//Sex
		this.maleRatio =  "1.1";
		this.femaleRatio =  "1.0";
		//madeYear
		this.after2018  = "1.2";
		this.before2018 = "1.0";
		
	}

	public String getAfter2018() {
		return after2018;
	}

	public void setAfter2018(String after2018) {
		this.after2018 = after2018;
	}

	public String getBefore2018() {
		return before2018;
	}

	public void setBefore2018(String before2018) {
		this.before2018 = before2018;
	}

	public int getInsuranceID() {
		return InsuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		InsuranceID = insuranceID;
	}

	public String getSmallRatio() {
		return smallRatio;
	}

	public void setSmallRatio(String smallRatio) {
		this.smallRatio = smallRatio;
	}

	public String getFreightRatio() {
		return freightRatio;
	}

	public void setFreightRatio(String freightRatio) {
		this.freightRatio = freightRatio;
	}

	public String getSedanRatio() {
		return sedanRatio;
	}

	public void setSedanRatio(String sedanRatio) {
		this.sedanRatio = sedanRatio;
	}

	public String getBusRatio() {
		return busRatio;
	}

	public void setBusRatio(String busRatio) {
		this.busRatio = busRatio;
	}

	public String getMotorCycleRatio() {
		return motorCycleRatio;
	}

	public void setMotorCycleRatio(String motorCycleRatio) {
		this.motorCycleRatio = motorCycleRatio;
	}

	public String getStudentRatio() {
		return studentRatio;
	}

	public void setStudentRatio(String studentRatio) {
		this.studentRatio = studentRatio;
	}

	public String getDeliverRatio() {
		return deliverRatio;
	}

	public void setDeliverRatio(String deliverRatio) {
		this.deliverRatio = deliverRatio;
	}

	public String getBusDriverRatio() {
		return busDriverRatio;
	}

	public void setBusDriverRatio(String busDriverRatio) {
		this.busDriverRatio = busDriverRatio;
	}

	public String getFreightDriverRatio() {
		return freightDriverRatio;
	}

	public void setFreightDriverRatio(String freightDriverRatio) {
		this.freightDriverRatio = freightDriverRatio;
	}

	public String getEtcRatio() {
		return etcRatio;
	}

	public void setEtcRatio(String etcRatio) {
		this.etcRatio = etcRatio;
	}

	public String getRatio20() {
		return ratio20;
	}

	public void setRatio20(String ratio20) {
		this.ratio20 = ratio20;
	}

	public String getRatio30() {
		return ratio30;
	}

	public void setRatio30(String ratio30) {
		this.ratio30 = ratio30;
	}

	public String getRatio40() {
		return ratio40;
	}

	public void setRatio40(String ratio40) {
		this.ratio40 = ratio40;
	}

	public String getRatio50() {
		return ratio50;
	}

	public void setRatio50(String ratio50) {
		this.ratio50 = ratio50;
	}

	public String getRatio60() {
		return ratio60;
	}

	public void setRatio60(String ratio60) {
		this.ratio60 = ratio60;
	}

	public String getMaleRatio() {
		return maleRatio;
	}

	public void setMaleRatio(String maleRatio) {
		this.maleRatio = maleRatio;
	}

	public String getFemaleRatio() {
		return femaleRatio;
	}

	public void setFemaleRatio(String femaleRatio) {
		this.femaleRatio = femaleRatio;
	}
	

}
