package Mail;

import java.util.ArrayList;

import Insurance.Insurance;

public interface MailList {
	public Mail search(int mailID);
	public boolean add(Mail mailID);
	public boolean delete(int mailID);
	public ArrayList<Mail> getMailList();
}
