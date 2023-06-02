package Interview;




/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class Interview {

	private String customerID , employeeID , askDate , processedDate, content;
	private String isProcessed; //0 : N 1 : Y.
	public Interview(){}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getAskDate() {
		return askDate;
	}
	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}
	public String getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}
	public String getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}