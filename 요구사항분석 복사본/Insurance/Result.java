package Insurance;

public class Result {
 // Insurance ID / Goal(3) / numSelled / statement ( 미달 . 달성 . 초과달성 ) /
// 보험 인가 일자 / 목표 일자 
	private String insuranceID;
	private String Goal;
	private String numSelled;
	private String statement;
	private String problem;
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getGoal() {
		return Goal;
	}
	public void setGoal(String goal) {
		Goal = goal;
	}
	public String getNumSelled() {
		return numSelled;
	}
	public void setNumSelled(String numSelled) {
		this.numSelled = numSelled;
	}
}
