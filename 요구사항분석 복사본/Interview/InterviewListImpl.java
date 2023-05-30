package Interview;

import java.util.ArrayList;

import Insurance.Insurance;
import apple.laf.JRSUIUtils.Tree;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 오전 8:20:44
 */
public class InterviewListImpl implements InterviewList {
	//attribute
	private ArrayList<Interview> interviewList;
	public Interview mInterview;
	public InterviewListImpl() {
		this.interviewList = new ArrayList<Interview>();
	}
	//getter setter
	@Override
	public ArrayList<Interview> getInterviewList(){return this.interviewList;}
	public void setInterviewList(ArrayList<Interview> interviews) {this.interviewList = interviews;}
	///
	@Override
	public boolean add(Interview interview) {
		if(this.interviewList.add(interview)) return true;
		return false;
	}

	@Override
	public boolean delelte(String customerID ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Interview search(String customerID) {
		for(Interview interview : this.interviewList) {
			if(interview.getCustomerID().equals(customerID)) return interview;
		}
		return null;
	}


}