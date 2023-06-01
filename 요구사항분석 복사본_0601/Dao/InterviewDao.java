package Dao;

import Interview.Interview;
import Interview.InterviewList;


public interface InterviewDao {
	public void create(Interview interview);
	public InterviewList retrieveProcessed();
	public InterviewList retrieveNoProcessed();
	public void update(String column,String updateQuery,String customerID);
	
}
