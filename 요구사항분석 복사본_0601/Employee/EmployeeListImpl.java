package Employee;

import java.util.ArrayList;

import Employee.Employee;

public class EmployeeListImpl implements EmployeeList {
	private ArrayList<Employee> employeeList;
	public EmployeeListImpl() {
		this.employeeList = new ArrayList<Employee>();
	}
	
	@Override
	public boolean add(Employee employee) {
		if(this.employeeList.add(employee)) return true;
		return false;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee search(int employeeID) {	
		for(Employee employee : this.employeeList) {
			if(employee.getEmployeeID() == employeeID) return employee;
		}
		return null;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

}
