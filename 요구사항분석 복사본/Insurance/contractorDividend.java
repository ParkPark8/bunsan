package Insurance;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 오전 8:20:43
 */
public class contractorDividend extends insuranceDividend {

	private int contractorDividend;

	public contractorDividend(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 보험상품의 예정이율 ,  가입자의 수신금리, 상세정보를 토대로 금리차 배당금을 시산
	 */
	public int estimateAdividend(){
		return 0;
	}

}