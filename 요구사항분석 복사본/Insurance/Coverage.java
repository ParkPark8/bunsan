package Insurance;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class Coverage  {

	private int coverageCost,insuranceID , coverageID ;
	private String coverageCondition;

	public Coverage(){

	}
	public int getCoverageCost() {return coverageCost;}
	public void setCoverageCost(int coverageCost) {this.coverageCost = coverageCost;}
	public int getInsuranceID() {return this.insuranceID;}
	public void setInsuranceID(int insuranceID) {this.insuranceID = insuranceID;}
	public String getCoverageCondition() {return coverageCondition;}
	public void setCoverageCondition(String coverageCondition) {this.coverageCondition = coverageCondition;}
	public void setCoverageID(int cID) {this.coverageID = cID;}
	public int getCoverageID() {return this.coverageID;}

}