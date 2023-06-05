package Mail;

import java.util.ArrayList;

public class MailListImpl implements MailList {
	private ArrayList<Mail> mailList;
	public MailListImpl() {
		this.mailList = new ArrayList<Mail>();
	}
	//getter setter
	public ArrayList<Mail> getMailList(){return this.mailList;}
	public void setMailList(MailList mailList) {this.mailList = this.mailList;}
	
	@Override
	public Mail search(int MailID) {
		for(Mail mails : this.mailList) {
			if(mails.getMailID() == MailID) return mails;
		}return null;
	}

	@Override
	public boolean add(Mail mail) {
		if(this.mailList.add(mail)) return true;
		return false;
	}

	@Override
	public boolean delete(int mailID) {
		// TODO Auto-generated method stub
		return false;
	}

}