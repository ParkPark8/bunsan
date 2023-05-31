package Contract;

import java.util.ArrayList;

/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public interface ContractList {
	public boolean add(Contract contract);
	public boolean delete();
	public Contract search(int contracrID);
	public void update();
	public ArrayList<Contract> getContractList();
	Contract searchIsContract(String insuranceID, String customerID);
}