package Dao;

import java.sql.ResultSet;

import Employee.Employee;
import Employee.EmployeeList;
import Employee.EmployeeListImpl;

public class EmployeeDaoImpl extends Dao implements EmployeeDao{
	public EmployeeDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public EmployeeList retrieve() {
		String query = "select * from employee";
		EmployeeList employeeList = new EmployeeListImpl();
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(resultSet.getInt("employeeID"));
				employee.setName(resultSet.getString("name"));
				employee.setNumSelled(resultSet.getString("numSelled"));
				employeeList.add(employee);
			}
			return employeeList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void update(String column, String updateQuery, Employee employee) {
		String employeeID = employee.getEmployeeID()+"";
		String query = "UPDATE Employee SET "+ column+" = "+updateQuery+" WHERE employeeID = '"+employeeID+"'";
		try {
			this.update(query);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
