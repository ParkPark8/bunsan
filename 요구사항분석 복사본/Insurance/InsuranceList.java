package Insurance;

import java.util.ArrayList;


/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public interface InsuranceList {

	public boolean add(Insurance insurance);

	public boolean delete(int insuranceId);

	public Insurance search(int insuranceID);
	public boolean update();
	public ArrayList<Insurance> getInsuranceList();

}