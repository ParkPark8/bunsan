package Employee;



/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:43
 */
public class Employee {

	private String name , numSelled;
	private int employeeID;


	public Employee(){

	}

	public int getEmployeeID() {return employeeID;}
	public void setEmployeeID(int employeeID) { this.employeeID = employeeID;}
	public String getName() {	return name;	}
	public void setName(String name) {		this.name = name;	}
	public String getNumSelled() {return this.numSelled;}
	public void setNumSelled(String numSelled) {this.numSelled = numSelled;}
}
