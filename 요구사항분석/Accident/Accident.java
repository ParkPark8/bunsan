package Accident;



public class Accident {

	private int accidentID ;
	private String accidentType ; //대인 , 대물 , 자차파손 ,등
	private String processNum ; // 1 : 고객이 접수 2 : 처
	private String customerID , contractID , date , time , location ;
	private String video , photo , mp3;
	public String getAccidentType() {
		return accidentType;
	}
	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	public String getProcessNum() {
		return processNum;
	}
	public void setProcessNum(String processNum) {
		this.processNum = processNum;
	}
	public int getAccidentID() {
		return accidentID;
	}
	public void setAccidentID(int accidentID) {
		this.accidentID = accidentID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMp3() {
		return mp3;
	}
	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}
}