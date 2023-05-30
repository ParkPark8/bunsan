package Insurance;

import Sales.sales;
import Sales.salesList;

/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public class Insurance{
	// Attributes
	private String insuranceName , productType;

	private int insuranceID,  insuranceFee;
	private boolean isPassed;
	
	// Composition 
	public Coverage m_hCoverage;
	public Coverage m_mCoverage;
	public Coverage m_lCoverage;
	public insuranceDividend m_insuranceDividend;
	public sales mSale;
	public Confirm m_confirm;
	public Ratio m_Ratio;
	public Result m_result;
	public Insurance(){
		this.m_hCoverage = new Coverage();
		this.m_mCoverage = new Coverage();
		this.m_lCoverage = new Coverage();
		this.mSale = new sales();
		this.isPassed = false;
		this.m_confirm = new Confirm();
		this.productType = "자동차종합보험";
		this.m_Ratio = new Ratio();
		this.m_result = new Result();
	}

	
	public int getInsuranceID() {return this.insuranceID;}
	public void setInsuranceID(int insuranceID) {this.insuranceID = insuranceID;}
	public String getInsuranceName() {return insuranceName;}
	public void setInsuranceName(String insuranceName) {this.insuranceName = insuranceName;}
	public String getProductType() {return this.productType;}
	public void setProductType(String productType) {this.productType = productType;}
	public int getInsuranceFee() {return insuranceFee;}
	public void setInsuranceFee(int insuranceFee) {this.insuranceFee = insuranceFee;}
	//Coverage
	public Coverage getHCoverage() {return m_hCoverage;}
	public void setHCoverage(Coverage hcoverage) {this.m_hCoverage=hcoverage;}
	
	public Coverage getMCoverage() {return m_mCoverage;}
	public void setMCoverage(Coverage mcoverage) {this.m_mCoverage = mcoverage;}
	
	public Coverage getLCoverage() {return this.m_hCoverage;}
	public void setLCoverage(Coverage lCoverage) {this.m_lCoverage = lCoverage;}
	//
	public insuranceDividend getM_insuranceDividend() {return m_insuranceDividend;}
	public void setM_insuranceDividend(insuranceDividend m_insuranceDividend) {this.m_insuranceDividend = m_insuranceDividend;}
	//sales
	public sales getM_sales() {return mSale;}
	public void setM_sales(sales msale) {	this.mSale = msale;}
	//passed
	public boolean isPassed() {return isPassed;}
	public void setPassed(boolean isPassed) {this.isPassed = isPassed;}
	//Confirm
	public Confirm getConfirm() {return this.m_confirm;}
	public void setConfirm(Confirm confirm) {this.m_confirm = confirm;}
	//Ratio
	public Ratio getRaito() {return this.m_Ratio;}
	public void setRatio(Ratio ratio) {this.m_Ratio = ratio;}
	//Result
	public Result getResult() {return this.m_result;}
	public void setResult(Result result) {this.m_result = result;}
	
	
}