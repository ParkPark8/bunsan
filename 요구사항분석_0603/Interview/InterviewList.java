package Interview;

import java.util.ArrayList;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public interface InterviewList {
	public boolean add(Interview interview);
	public boolean delelte(String customerID );
	public Interview search(String customerID);
	public ArrayList<Interview> getInterviewList();
}