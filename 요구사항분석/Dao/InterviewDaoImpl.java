package Dao;

import java.sql.ResultSet;

import Customer.Customer;
import Interview.Interview;
import Interview.InterviewList;
import Interview.InterviewListImpl;

public class InterviewDaoImpl extends Dao implements InterviewDao {
	
	public InterviewDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public InterviewList retrieveProcessed() {
		String query = "select * from Interview where isProcessed = '1' ";
		InterviewList interviewList = new InterviewListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Interview interview = new Interview();
				interview.setCustomerID(resultSet.getString("customerID"));
				interview.setAskDate(resultSet.getString("askDate"));
				interview.setEmployeeID(resultSet.getString("employeeID"));
				interview.setIsProcessed("1");
				interview.setProcessedDate(resultSet.getString("processedDate"));
				interview.setContent(resultSet.getString("content"));
				interviewList.add(interview);
			}
			return interviewList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public InterviewList retrieveNoProcessed() {
		String query = "select * from Interview where isProcessed = '0' ";
		InterviewList interviewList = new InterviewListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Interview interview = new Interview();
				interview.setCustomerID(resultSet.getString("customerID"));
				interview.setAskDate(resultSet.getString("askDate"));
				interview.setEmployeeID(resultSet.getString("employeeID"));
				interview.setIsProcessed("0");
				interview.setProcessedDate(resultSet.getString("processedDate"));
				interview.setContent(resultSet.getString("content"));
				interviewList.add(interview);
			}
			return interviewList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	@Override
	public void create(Interview interview) {
		String query = "insert into Interview (customerID , employeeID , askDate , processedDate , isProcessed , content ) values ("+
	" '" +interview.getCustomerID()+ "', "+
	" '" +interview.getEmployeeID()+ "', "+
	" '" + interview.getAskDate()+ "', "+
	" '" +interview.getProcessedDate() + "', "+
	" '" + interview.getIsProcessed()+ "', "+
	" '" + interview.getContent()+ "') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String column,String updateQeury, String customerID) {
		String query = "UPDATE Interview SET "+ column+" = "+updateQeury+" WHERE customerID = '" + customerID+ "'";
		//UPDATE `new_schema`.`Customer` SET isInterview = '1' WHERE (`customerID` = 'pdh');		
		try {
				this.update(query);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
