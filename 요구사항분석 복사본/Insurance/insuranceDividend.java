package Insurance;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class insuranceDividend extends Insurance {

	private int compensation;
	/**
	 * 1. �������� ����  2. ���� ������ ���  3. ����ݿ� �߰��Ͽ� ����
	 */
	private String paymentMethod;
	private boolean paymentObligation;

	public insuranceDividend(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * �����ǰ�� �������� ,  �������� ���űݸ�, �������� ���� �ݸ��� ������ �û�
	 */
	public int estimateAdividend(){
		return 0;
	}

}