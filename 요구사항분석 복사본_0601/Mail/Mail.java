package Mail;

public class Mail {
	// customerID , employeeName , Date , content , title
	private int mailID;
	private String customerID;
	private String employeeID;
	private String date;
	private String content;
	private String title;
	
	public int getMailID() {
		return this.mailID;
	}
	public void setMailID(int mailID) {
		this.mailID = mailID;
	}
	public String getCustomerID() {
		return this.customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getEmployeeID() {
		return this.employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
