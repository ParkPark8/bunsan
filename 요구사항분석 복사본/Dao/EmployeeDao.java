package Dao;

import Employee.Employee;
import Employee.EmployeeList;

public interface EmployeeDao {
	public EmployeeList retrieve();
	public void update(String column , String updateQuery , Employee employee);
}
