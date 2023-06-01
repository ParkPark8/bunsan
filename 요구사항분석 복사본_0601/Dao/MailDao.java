package Dao;

import Mail.Mail;
import Mail.MailList;

public interface MailDao {
	public int create(Mail mail);
	public MailList retrieveByCustomerID(String customerID);
	public void delete(int mailID);
}
