package Customer;

import java.util.ArrayList;

/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public interface CustomerList {

	public boolean add(Customer customer);

	public boolean delete();

	public ArrayList getCustomerList();

	public Customer search(String customerID);

	public void update();

}