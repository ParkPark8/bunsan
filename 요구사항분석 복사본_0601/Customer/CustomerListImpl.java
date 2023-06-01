package Customer;

import java.util.ArrayList;

/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public class CustomerListImpl implements CustomerList{

	private ArrayList<Customer> CustomerList;
	public CustomerListImpl(){
		this.CustomerList = new ArrayList<Customer>();
	}
	//getter/setter
	public ArrayList<Customer> getCustomerList(){return this.CustomerList;}
	public void setCustomerList(ArrayList<Customer> customers) {this.CustomerList = customers;}
	//Override 
	@Override
	public Customer search(String customerID) {
		for(Customer customers : this.CustomerList) {
			if(customers.getCustomerID().equals(customerID)) return customers;
		}
		return null;
	}
	@Override
	public boolean add(Customer customer){
		if(this.CustomerList.add(customer)) return true;
		return false;
	}
	@Override
	public boolean delete(){
		return false;
	}
	public void update(){

	}


}