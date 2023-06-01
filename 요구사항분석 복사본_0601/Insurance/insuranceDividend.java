package Insurance;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 오전 8:20:44
 */
public class insuranceDividend extends Insurance {

	private int compensation;
	/**
	 * 1. 현금으로 지급  2. 기존 보험료로 상계  3. 보험금에 추가하여 지급
	 */
	private String paymentMethod;
	private boolean paymentObligation;

	public insuranceDividend(){

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