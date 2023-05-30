package Employee;

import java.util.ArrayList;

public interface EmployeeList {
	public boolean add(Employee employee);

	public void delete();

	public void revive();

	public void search();

	Employee search(int employeeID);

}
