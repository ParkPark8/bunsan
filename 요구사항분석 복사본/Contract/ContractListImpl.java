package Contract;

import java.util.ArrayList;

/**
 * @author a8231
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public class ContractListImpl implements ContractList {
	private ArrayList<Contract> ContractList;
	public ContractListImpl() {
		this.ContractList = new ArrayList<Contract>();
	}
	//getter/setter
	public ArrayList<Contract> getContractList(){return this.ContractList;}
	public void setContractList(ArrayList<Contract> contracts) {this.ContractList = contracts;}
	//Override
	@Override
	public boolean add(Contract contract) {
		if(this.ContractList.add(contract)) return true;
		return false;
	}
	@Override
	public Contract search(int contracrID) {
		for(Contract contracts : this.ContractList) {
			if(contracts.getContractID() == contracrID ) return contracts;
		}
		return null;
	}


	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}