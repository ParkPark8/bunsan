package Dao;


import java.sql.ResultSet;

import Mail.Mail;
import Mail.MailList;
import Mail.MailListImpl;

public class MailDaoImpl extends Dao implements MailDao{
	public MailDaoImpl() {
		try {
			this.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int create(Mail mail) {
		int mailID = 0;
		try {
			String query = "insert into Mail (customerID , employeeID , date , content , title ) values ("+
		" '"+ mail.getCustomerID()+ "', "+
		" '"+ mail.getEmployeeID()+ "', "+
		" '"+ mail.getDate()+ "', "+
		" '"+ mail.getContent()+ "', "+
		" '"+ mail.getTitle()+ "') ";
			
		this.execute(query);
		
		query = "select LAST_INSERT_ID() as ID";
		ResultSet resultSet = this.retrieve(query);
		while(resultSet.next()) {
			mailID = resultSet.getInt("ID");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mailID;
	}

	@Override
	public MailList retrieveByCustomerID(String customerID) {
		String query = "select * from Mail WHERE customerID = '"+ customerID+ "'";
		MailList mailList = new MailListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Mail mail = new Mail();
				mail.setContent(resultSet.getString("content"));
				mail.setCustomerID(resultSet.getString("customerID"));
				mail.setDate(resultSet.getString("date"));
				mail.setEmployeeID(resultSet.getString("employeeID"));
				mail.setMailID(resultSet.getInt("mailID"));
				mail.setTitle(resultSet.getString("title"));
				mailList.add(mail);
			}
			return mailList;
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

	@Override
	public void delete(int mailID) {
		//DELETE FROM `new_schema`.`Mail` WHERE (`mailID` = '1');
		String query = "DELETE FROM Mail WHERE mailID = '" + mailID+"'" ; 
		
		
	}

}
