package Employee;

import java.util.ArrayList;

import Contract.Contract;

public interface EmployeeList {
	public boolean add(Employee employee);

	public void delete();

	public void revive();

	public void search();

	Employee search(int employeeID);
	public ArrayList<Employee> getEmployeeList();


}
